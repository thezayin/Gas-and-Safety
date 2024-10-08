package com.thezayin.domain.repository

import com.thezayin.domain.model.HomeProdModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetProductsRepository {
    fun getProducts(): Flow<Response<List<HomeProdModel>>>
    fun getProductsWithImages(list: List<HomeProdModel>): Flow<Response<List<HomeProdModel>>>
}