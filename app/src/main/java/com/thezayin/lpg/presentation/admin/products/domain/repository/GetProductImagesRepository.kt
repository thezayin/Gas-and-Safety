package com.thezayin.lpg.presentation.admin.products.domain.repository

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.products.domain.model.AdminProductModel
import com.thezayin.lpg.presentation.admin.products.domain.model.ProductImageModel
import kotlinx.coroutines.flow.Flow

interface GetProductImagesRepository {
    fun getImages(productList:List<AdminProductModel>): Flow<Response<List<ProductImageModel>>>
}