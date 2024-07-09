package com.thezayin.adminproducts.domain.repository

import com.thezayin.framework.utils.Response
import com.thezayin.entities.ProductModel
import com.thezayin.entities.HomeProductsModel
import kotlinx.coroutines.flow.Flow

interface GetProductImagesRepository {
    fun getImages(productList:List<ProductModel>): Flow<Response<List<HomeProductsModel>>>
}