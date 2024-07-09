package com.thezayin.adminproducts.domain.repository

import com.thezayin.framework.utils.Response
import com.thezayin.entities.ProductModel
import kotlinx.coroutines.flow.Flow

interface AdminProductRepository {
    fun getProducts(): Flow<Response<List<ProductModel>>>
}