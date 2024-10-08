package com.thezayin.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.domain.model.OrderModel
import com.thezayin.domain.repository.MyOrdersRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MyOrderRepositoryImpl(private val firestore: FirebaseFirestore) : MyOrdersRepository {
    override fun getMyOrders(userId: String): Flow<Resource<List<OrderModel>>> =
        callbackFlow {
            try {
                trySend(Resource.Loading)
                val orders = firestore.collection("user_orders")
                    .whereEqualTo("userID", userId)
                    .addSnapshotListener { snapShot, error ->
                        val resource = if (snapShot != null) {
                            val productList =
                                snapShot.toObjects(OrderModel::class.java)
                            Resource.Success(productList)
                        } else {
                            Resource.Error(
                                error?.message ?: error.toString()
                            )
                        }
                        trySend(resource).isSuccess
                    }
                awaitClose {
                    orders.remove()
                    channel.close()
                }
            } catch (e: Exception) {
                trySend(
                    Resource.Error(
                        e.localizedMessage ?: "An error occurred"
                    )
                )
            }
        }
}