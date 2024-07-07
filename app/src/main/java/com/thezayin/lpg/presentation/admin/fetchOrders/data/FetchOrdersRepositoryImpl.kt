package com.thezayin.lpg.presentation.admin.fetchOrders.data

import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.model.FetchOrdersModel
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.repository.FetchOrdersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FetchOrdersRepositoryImpl(private val firestore: FirebaseFirestore) : FetchOrdersRepository {
    override fun getOrdersRepository(): Flow<Response<List<FetchOrdersModel>>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val snapshotListener =
                firestore.collection("user_orders").addSnapshotListener { snapShot, error ->
                    val response = if (snapShot != null) {
                        val productList = snapShot.toObjects(FetchOrdersModel::class.java)
                        Response.Success<List<FetchOrdersModel>>(productList).apply {
                        }
                    } else {
                        Response.Error(error?.message ?: error.toString())
                    }
                    trySend(response).isSuccess.apply {
                    }
                }
            awaitClose {
                snapshotListener.remove()
                channel.close()
            }
        } catch (e: Exception) {
            trySend(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}