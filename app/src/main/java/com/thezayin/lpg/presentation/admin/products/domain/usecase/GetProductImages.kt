package com.thezayin.lpg.presentation.admin.products.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.products.domain.model.AdminProductModel
import com.thezayin.lpg.presentation.admin.products.domain.model.ProductImageModel
import com.thezayin.lpg.presentation.admin.products.domain.repository.GetProductImagesRepository
import kotlinx.coroutines.flow.Flow

interface GetProductImages :
    suspend (List<AdminProductModel>) -> Flow<Response<List<ProductImageModel>>>

class GetProductImagesImpl(private val repository: GetProductImagesRepository) : GetProductImages {
    override suspend fun invoke(productList: List<AdminProductModel>): Flow<Response<List<ProductImageModel>>> =
        repository.getImages(productList)
}