package com.thezayin.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.domain.GetProductsRepository
import kotlinx.coroutines.flow.Flow

interface GetProducts : suspend () -> Flow<Response<List<com.thezayin.entities.HomeProductsModel>>>

class GetProductsImpl(
    private val repository: com.thezayin.domain.GetProductsRepository
) : GetProducts {
    override suspend fun invoke(): Flow<Response<List<com.thezayin.entities.HomeProductsModel>>> =
        repository.getProducts()
}