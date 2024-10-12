package com.thezayin.userbuy.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.entities.CartModel
import com.thezayin.entities.ProfileModel
import com.thezayin.entities.UserOrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.userbuy.domain.repository.PlaceOrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class PlaceOrderRepositoryImpl(private val fireStore: FirebaseFirestore) : PlaceOrderRepository {
    private var operationSuccessFull = false
    override fun placeOrder(
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
    ): Flow<Response<Boolean>> =
        flow {
            try {
                emit(Response.Loading)
                val orderId = fireStore.collection("user_order").document().id
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
                    orderStatus = orderStatus,
                    orderDateTime = Timestamp.now(),
                    paymentMethod = paymentMethod,
                    totalAmount = totalAmount,
                    orders = orders,
                )
                fireStore.collection("user_order").document(orderId).set(order)
                    .addOnSuccessListener {
                        operationSuccessFull = true
                    }.addOnFailureListener {
                        operationSuccessFull = false
                    }.await()

                val userInfo = ProfileModel(
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