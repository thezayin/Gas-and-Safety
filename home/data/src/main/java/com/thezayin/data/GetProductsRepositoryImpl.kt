package com.thezayin.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thezayin.domain.GetProductsRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class GetProductsRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : GetProductsRepository {
    override fun getProducts(): Flow<Response<List<com.thezayin.entities.HomeProductsModel>>> =
        callbackFlow {
            trySend(Response.Loading)
            val snapshotListener =
                firestore.collection("products").addSnapshotListener { snapShot, error ->
                    val response = if (snapShot != null) {
                        val productList =
                            snapShot.toObjects(com.thezayin.entities.HomeProductsModel::class.java)
                        Response.Success<List<com.thezayin.entities.HomeProductsModel>>(productList)
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

    override fun getProductsWithImages(list: List<com.thezayin.entities.HomeProductsModel>): Flow<Response<List<com.thezayin.entities.HomeProductsModel>>> =
        callbackFlow {
            val listPro = mutableListOf<com.thezayin.entities.HomeProductsModel>()
            try {
                trySend(Response.Loading)
                list.forEach { product ->
                    product.imageUrl?.let {
                        storage.reference.child(it)
                            .downloadUrl.addOnSuccessListener { uri ->
                                listPro.add(
                                    com.thezayin.entities.HomeProductsModel(
                                        id = product.id,
                                        name = product.name,
                                        description = product.description,
                                        price = product.price,
                                        imageUrl = product.imageUrl,
                                        imageUri = uri,
                                        date = product.date
                                    )
                                )
                            }.addOnFailureListener { error ->
                                trySend(Response.Error(error.localizedMessage ?: error.toString()))
                            }.await()
                    }
                }
                trySend(Response.Success(listPro))
                awaitClose()
            } catch (e: Exception) {
                trySend(Response.Error(e.localizedMessage ?: e.toString()))
            }
        }

}