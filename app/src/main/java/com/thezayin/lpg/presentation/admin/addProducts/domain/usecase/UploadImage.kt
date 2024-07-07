package com.thezayin.lpg.presentation.admin.addProducts.domain.usecase

import android.net.Uri
import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.addProducts.domain.repository.AddProductRepository
import kotlinx.coroutines.flow.Flow

interface UploadImage : suspend (Uri) -> Flow<Response<String>>

class UploadImageImpl(private val repository: AddProductRepository) : UploadImage {
    override suspend fun invoke(image: Uri): Flow<Response<String>> =
        repository.addImage(image)
}