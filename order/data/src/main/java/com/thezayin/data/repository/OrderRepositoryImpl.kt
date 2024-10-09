package com.thezayin.data.repository

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
        try {
            emit(Resource.Loading) // Emit loading state
            // Generate a unique order ID
            val orderId = fireStore.collection("user_orders").document().id

            // Create a new UserOrderModel object
            val order = UserOrderModel(
                id = orderId,
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

            // Attempt to save the order to Firestore
            fireStore.collection("user_orders").document(orderId).set(order).await()

            // Emit success state after successful saving
            emit(Resource.Success(true))
        } catch (e: Exception) {
            // Emit error state if there was a failure
            emit(Resource.Error(e.message ?: e.toString()))
        }
    }
}