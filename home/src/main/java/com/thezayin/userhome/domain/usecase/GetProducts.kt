package com.thezayin.userhome.domain.usecase

import com.thezayin.entities.HomeProductsModel
import com.thezayin.framework.utils.Response
import com.thezayin.userhome.domain.repository.GetProductsRepository
import kotlinx.coroutines.flow.Flow

interface GetProducts : suspend () -> Flow<Response<List<HomeProductsModel>>>

class GetProductsImpl(
    private val repository: GetProductsRepository
) : GetProducts {
    override suspend fun invoke(): Flow<Response<List<HomeProductsModel>>> =
        repository.getProducts()
}