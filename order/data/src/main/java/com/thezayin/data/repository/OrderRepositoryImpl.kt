package com.thezayin.data.repository

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.databases.model.CartModel
import com.thezayin.domain.model.UserOrderModel
import com.thezayin.domain.repository.OrderRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class OrderRepositoryImpl(private val fireStore: FirebaseFirestore) : OrderRepository {

    /**
     * Places an order in the Firestore database.
     *
     * @param userID The unique identifier of the user placing the order.
     * @param name The name of the user.
     * @param phoneNumber The user's phone number.
     * @param address The delivery address for the order.
     * @param area The area of the delivery address.
     * @param city The city of the delivery address.
     * @param email The user's email address (optional).
     * @param message Any additional message or instructions for the order (optional).
     * @param orderDate The date the order is placed.
     * @param orderTime The time the order is placed.
     * @param orderDateTime The complete date and time of the order.
     * @param orderStatus The current status of the order.
     * @param paymentMethod The method of payment used for the order.
     * @param totalAmount The total amount for the order.
     * @param orders A list of cart items included in the order.
     * @return A Flow emitting Resource indicating the success or failure of the operation.
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
        Log.d("jejRepFlow","Creating order for user: $userID")
        emit(Resource.Loading) // Emit loading state
        try {
            Log.d("jejRepTry","Creating order for user: $userID")
            // Reference to the counter document
            val counterRef = fireStore.collection("counters").document("orderCounter")

            // Firestore transaction to increment counter and generate unique order ID
            val newOrderId = fireStore.runTransaction { transaction ->
                val snapshot = transaction.get(counterRef)
                if (!snapshot.exists()) {
                    throw Exception("Counter document does not exist!")
                }

                val lastOrderId = snapshot.getLong("lastOrderId") ?: throw Exception("lastOrderId not found!")

                val incrementedOrderId = lastOrderId + 1

                // Update the counter
                transaction.update(counterRef, "lastOrderId", incrementedOrderId)

                // Format the order ID to 8 digits with leading zeros
                String.format("%06d", incrementedOrderId)
            }.await()

            // Create a new UserOrderModel object with the generated orderId
            val order = UserOrderModel(
                id = newOrderId, // Use the generated 8-digit ID
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

            // Attempt to save the order to Firestore with the 8-digit orderId as the document ID
            fireStore.collection("user_order").document(newOrderId).set(order).await()

            // Emit success state after successful saving
            emit(Resource.Success(true))
        } catch (e: Exception) {
            // Emit error state if there was a failure
            emit(Resource.Error(e.message ?: e.toString()))
        }
    }
}