package com.thezayin.domain.usecase

import com.thezayin.domain.model.HomeProdModel
import com.thezayin.domain.repository.GetProductsRepository
import kotlinx.coroutines.flow.Flow

interface GetProducts :
    suspend () -> Flow<com.thezayin.framework.utils.Response<List<HomeProdModel>>>

class GetProductsImpl(
    private val repository: GetProductsRepository
) : GetProducts {
    override suspend fun invoke(): Flow<com.thezayin.framework.utils.Response<List<HomeProdModel>>> =
        repository.getProducts()
}