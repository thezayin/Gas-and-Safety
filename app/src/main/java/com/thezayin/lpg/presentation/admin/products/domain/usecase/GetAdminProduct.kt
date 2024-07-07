package com.thezayin.lpg.presentation.admin.products.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.products.domain.model.AdminProductModel
import com.thezayin.lpg.presentation.admin.products.domain.repository.AdminProductRepository
import kotlinx.coroutines.flow.Flow

interface GetAdminProduct : suspend () -> Flow<Response<List<AdminProductModel>>>

class GetAdminProductImpl(private val productRepository: AdminProductRepository) : GetAdminProduct {
    override suspend fun invoke(): Flow<Response<List<AdminProductModel>>> =
        productRepository.getProducts()
}