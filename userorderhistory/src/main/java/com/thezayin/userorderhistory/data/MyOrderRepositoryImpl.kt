package com.thezayin.userorderhistory.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.userorderhistory.domain.repository.MyOrdersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MyOrderRepositoryImpl(private val firestore: FirebaseFirestore) : MyOrdersRepository {
    override fun getMyOrders(userId: String): Flow<Response<List<OrderModel>>> =
        callbackFlow {
            try {
                trySend(Response.Loading)
                val orders = firestore.collection("user_orders")
                    .whereEqualTo("userID", userId)
                    .addSnapshotListener { snapShot, error ->
                        val response = if (snapShot != null) {
                            Log.d("response", "${snapShot.documents}")
                            val productList =
                                snapShot.toObjects(OrderModel::class.java).apply {
                                    Log.d("response", "${this}")
                                }
                            Response.Success(productList).apply {
                                Log.d("response", "${productList}")
                            }
                        } else {
                            Response.Error(error?.message ?: error.toString())
                        }
                        trySend(response).isSuccess
                    }
                awaitClose {
                    orders.remove()
                    channel.close()
                }
            } catch (e: Exception) {
                trySend(Response.Error(e.localizedMessage ?: "An error occurred"))
            }
        }
}