package com.thezayin.userhome.domain.repository

import com.thezayin.entities.HomeProductsModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetProductsRepository {
    fun getProducts(): Flow<Response<List<HomeProductsModel>>>
    fun getProductsWithImages(list: List<HomeProductsModel>): Flow<Response<List<HomeProductsModel>>>
}