package com.thezayin.lpg.presentation.admin.products.data

import com.google.firebase.storage.FirebaseStorage
import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.products.domain.model.AdminProductModel
import com.thezayin.lpg.presentation.admin.products.domain.model.ProductImageModel
import com.thezayin.lpg.presentation.admin.products.domain.repository.GetProductImagesRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class GetProductImagesRepositoryImpl(private val storage: FirebaseStorage) :
    GetProductImagesRepository {
    val list = mutableListOf<ProductImageModel>()
    override fun getImages(productList: List<AdminProductModel>): Flow<Response<List<ProductImageModel>>> =
        callbackFlow {
            trySend(Response.Loading)
            productList.forEach { product ->
                product.imageUrl?.let { it ->
                    storage.reference.child(it)
                        .downloadUrl.addOnSuccessListener { uri ->
                            list.add(
                                ProductImageModel(
                                    id = product.id,
                                    name = product.name,
                                    description = product.description,
                                    price = product.price,
                                    imageUrl = product.imageUrl,
                                    imageUri = uri,
                                    date = product.date
                                )
                            )
                        }.addOnFailureListener {
                            trySend(Response.Error(it.localizedMessage ?: it.toString()))
                        }.await()
                }
            }
            trySend(Response.Success(list))
            awaitClose()
        }
}