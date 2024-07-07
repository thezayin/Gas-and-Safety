package com.thezayin.lpg.presentation.admin.product_details.domain.usecase

import android.graphics.Bitmap
import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.product_details.domain.repository.ProDetailsRepository
import kotlinx.coroutines.flow.Flow

interface UpdateAdminProduct :
    suspend (String, String, String, String, String, String) -> Flow<Response<Boolean>>

class UpdateAdminProductIml(private val repository: ProDetailsRepository) : UpdateAdminProduct {
    override suspend fun invoke(
        id: String,
        name: String,
        description: String,
        price: String,
        date: String,
        imageUrl: String
    ): Flow<Response<Boolean>> = repository.updateProduct(id, name, description, price, date, imageUrl)
}