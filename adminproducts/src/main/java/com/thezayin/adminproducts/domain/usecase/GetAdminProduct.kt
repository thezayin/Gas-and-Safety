package com.thezayin.adminproducts.domain.usecase

import com.thezayin.adminproducts.domain.repository.AdminProductRepository
import com.thezayin.entities.ProductModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetAdminProduct : suspend () -> Flow<Response<List<ProductModel>>>

class GetAdminProductImpl(private val productRepository: AdminProductRepository) :
    GetAdminProduct {
    override suspend fun invoke(): Flow<Response<List<ProductModel>>> =
        productRepository.getProducts()
}