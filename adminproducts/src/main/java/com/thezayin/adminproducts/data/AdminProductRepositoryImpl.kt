package com.thezayin.adminproducts.data

import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.adminproducts.domain.repository.AdminProductRepository
import com.thezayin.entities.ProductModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AdminProductRepositoryImpl(
    private val firestore: FirebaseFirestore
) : AdminProductRepository {
    override fun getProducts(): Flow<Response<List<ProductModel>>> =
        callbackFlow {
            trySend(Response.Loading)
            val snapshotListener =
                firestore.collection("products").addSnapshotListener { snapShot, error ->
                    val response = if (snapShot != null) {
                        val productList = snapShot.toObjects(ProductModel::class.java)
                        Response.Success<List<ProductModel>>(productList)
                    } else {
                        Response.Error(error?.message ?: error.toString())
                    }
                    trySend(response).isSuccess
                }
            awaitClose {
                snapshotListener.remove()
                channel.close()
            }
        }
}