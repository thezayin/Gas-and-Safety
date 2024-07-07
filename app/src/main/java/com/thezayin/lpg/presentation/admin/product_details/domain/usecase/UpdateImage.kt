package com.thezayin.lpg.presentation.admin.product_details.domain.usecase

import android.net.Uri
import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.product_details.domain.repository.ProDetailsRepository
import kotlinx.coroutines.flow.Flow

interface UpdateImage : suspend (Uri) -> Flow<Response<String>>
class UpdateImageImpl(private val repository: ProDetailsRepository) : UpdateImage {
    override suspend fun invoke(uri: Uri): Flow<Response<String>> = repository.updateImage(uri)
}