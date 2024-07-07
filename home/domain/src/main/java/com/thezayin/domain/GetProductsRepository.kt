package com.thezayin.domain

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetProductsRepository {
    fun getProducts(): Flow<Response<List<com.thezayin.entities.HomeProductsModel>>>
    fun getProductsWithImages(list:List<com.thezayin.entities.HomeProductsModel>): Flow<Response<List<com.thezayin.entities.HomeProductsModel>>>
}