package com.thezayin.adminproductdetails.domain.usecase

import com.thezayin.adminproductdetails.domain.repository.ProDetailsRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface DeleteAdminProduct : suspend (String) -> Flow<Response<Boolean>>
class DeleteAdminProductImpl(private val repository: ProDetailsRepository) :
    DeleteAdminProduct {
    override suspend fun invoke(id: String): Flow<Response<Boolean>> = repository.deleteProduct(id)
}