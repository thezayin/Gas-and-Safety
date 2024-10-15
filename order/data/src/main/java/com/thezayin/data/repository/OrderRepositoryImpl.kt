package com.thezayin.data.repository

import android.content.Context
import android.util.Log
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.assets.R
import com.thezayin.databases.model.CartModel
import com.thezayin.domain.model.UserOrderModel
import com.thezayin.domain.repository.OrderRepository
import com.thezayin.framework.utils.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class OrderRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val httpClient: HttpClient,
    private val context: Context // Provide the context to open the InputStream multiple times
) : OrderRepository {

    /**
     * Places an order in the Firestore database.
     */
    override fun createOrder(
        userID: String,
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String?,
        message: String?,
        orderDate: String,
        orderTime: String,
        orderDateTime: String,
        orderStatus: String,
        paymentMethod: String,
        totalAmount: String,
        orders: List<CartModel>
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            // Reference to the counter document
            val counterRef = fireStore.collection("counters").document("orderCounter")

            // Firestore transaction to increment counter and generate unique order ID
            val newOrderId = fireStore.runTransaction { transaction ->
                val snapshot = transaction.get(counterRef)
                if (!snapshot.exists()) throw Exception("Counter document does not exist!")
                val lastOrderId =
                    snapshot.getLong("lastOrderId") ?: throw Exception("lastOrderId not found!")
                val incrementedOrderId = lastOrderId + 1

                // Update the counter
                transaction.update(counterRef, "lastOrderId", incrementedOrderId)

                // Format the order ID to 8 digits with leading zeros
                String.format("%06d", incrementedOrderId)
            }.await()

            // Create a new UserOrderModel object with the generated orderId
            val order = UserOrderModel(
                id = newOrderId,
                userID = userID,
                name = name,
                phoneNumber = phoneNumber,
                email = email,
                address = address,
                area = area,
                city = city,
                message = message,
                orderDate = orderDate,
                orderTime = orderTime,
                orderDateTime = Timestamp.now(),
                orderStatus = orderStatus,
                paymentMethod = paymentMethod,
                totalAmount = totalAmount,
                orders = orders
            )

            // Save the order in Firestore
            fireStore.collection("user_order").document(newOrderId).set(order).await()

            // Send FCM notification to the admin
            sendOrderNotificationToAdmin(newOrderId)

            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.toString()))
        }
    }

    // Send FCM notification to admin
    private suspend fun sendOrderNotificationToAdmin(orderId: String) {
        val adminDoc = fireStore.collection("admin_tokens").document("admin123").get().await()

        if (adminDoc.exists()) {
            val tokens = adminDoc.get("fcmTokens") as? List<String>
            tokens?.forEach { token ->
                sendNotificationToFCM(token, orderId)
            }
        }
    }

    // Send notification using FCM API
    private suspend fun sendNotificationToFCM(token: String, orderId: String) {
        withContext(Dispatchers.IO) {  // Ensure this runs on a background thread
            try {
                val accessToken = getAccessToken()
                if (accessToken == null) {
                    Log.e("FCM", "Failed to send notification: access token is null")
                    return@withContext
                }

                Log.d("FCM", "Access token: $accessToken")
                val url = "https://fcm.googleapis.com/v1/projects/safe-gass/messages:send"
                val jsonPayload = """
                {
                  "message": {
                    "token": "$token",
                    "notification": {
                      "title": "New Order",
                      "body": "Order ID $orderId has been placed"
                    },
                    "data": {
                      "orderId": "$orderId",
                      "message": "A new order has been placed"
                    }
                  }
                }
                """.trimIndent()

                // Perform network request in the background
                val response: HttpResponse = httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    header("Authorization", "Bearer $accessToken")
                    setBody(jsonPayload)
                }

                if (response.status == HttpStatusCode.OK) {
                    Log.d("FCM", "Notification sent successfully: ${response.bodyAsText()}")
                } else {
                    Log.e("FCM", "Error sending notification: ${response.status}")
                }
            } catch (e: Exception) {
                Log.e("FCM", "Error sending notification: ${e.message}", e)
            }
        }
    }

    // Method to load GoogleCredentials when needed
    private fun loadGoogleCredentials(): GoogleCredentials? {
        return try {
            val serviceAccountStream = context.resources.openRawResource(R.raw.service_account)
            GoogleCredentials.fromStream(serviceAccountStream.buffered())
                .createScoped(listOf("https://www.googleapis.com/auth/cloud-platform"))
        } catch (e: Exception) {
            Log.e("FCM", "Failed to load Google credentials: ${e.message}", e)
            null
        }
    }

    // Method to retrieve access token
    private fun getAccessToken(): String? {
        return try {
            val googleCredentials = loadGoogleCredentials()
            googleCredentials?.refreshIfExpired()
            val token = googleCredentials?.accessToken
            if (token != null) {
                token.tokenValue
            } else {
                Log.e("FCM", "Access token is null")
                null
            }
        } catch (e: Exception) {
            Log.e("FCM", "Failed to get access token: ${e.message}", e)
            null
        }
    }
}