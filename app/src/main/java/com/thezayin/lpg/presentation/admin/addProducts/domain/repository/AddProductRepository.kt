package com.thezayin.lpg.presentation.admin.addProducts.domain.repository

import android.graphics.Bitmap
import android.net.Uri
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface AddProductRepository {
    fun addProduct(
        name: String,
        description: String,
        price: String,
        date: String,
        imageUrl: String
    ): Flow<Response<Boolean>>
    fun addImage(image: Uri): Flow<Response<String>>
}