package com.thezayin.lpg.presentation.admin.product_details.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.product_details.domain.repository.ProDetailsRepository
import kotlinx.coroutines.flow.Flow

interface DeleteAdminProduct : suspend (String) -> Flow<Response<Boolean>>
class DeleteAdminProductImpl(private val repository: ProDetailsRepository) : DeleteAdminProduct {
    override suspend fun invoke(id: String): Flow<Response<Boolean>> = repository.deleteProduct(id)
}