package com.thezayin.data

import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.domain.PlaceOrderRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class PlaceOrderRepositoryImpl(private val fireStore: FirebaseFirestore) : PlaceOrderRepository {
    private var operationSuccessFull = false
    override fun placeOrder(
        userID: String,
        name: String,
        phoneNumber: String,
        email: String?,
        address: String,
        message: String?,
        orderDate: String,
        orderTime: String,
        orderDateTime: String,
        orderStatus: String,
        paymentMethod: String,
        totalAmount: String,
        orders: List<com.thezayin.entities.CartModel>
    ): Flow<Response<Boolean>> =
        flow {
            try {
                emit(Response.Loading)
                val orderId = fireStore.collection("user_orders").document().id
                val order = com.thezayin.entities.UserOrderModel(
                    id = orderId,
                    userId = userID,
                    name = name,
                    phoneNumber = phoneNumber,
                    email = email,
                    address = address,
                    message = message,
                    orderDate = orderDate,
                    orderTime = orderTime,
                    orderStatus = orderStatus,
                    orderDateTime = orderDateTime,
                    paymentMethod = paymentMethod,
                    totalAmount = totalAmount,
                    orders = orders,
                )
                fireStore.collection("user_orders").document(orderId).set(order)
                    .addOnSuccessListener {
                        operationSuccessFull = true
                    }.addOnFailureListener {
                        operationSuccessFull = false
                    }.await()

                val userInfo = com.thezayin.entities.UserDataModel(
                    name = name,
                    phoneNumber = phoneNumber,
                    email = email,
                    address = address,
                )

                fireStore.collection("users_data").document("info").set(userInfo)
                    .addOnSuccessListener {
                    }.addOnFailureListener {
                    }.await()

                emit(Response.Success(operationSuccessFull))
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: e.toString()))
            }
        }
}