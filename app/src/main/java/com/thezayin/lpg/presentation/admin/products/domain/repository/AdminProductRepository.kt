package com.thezayin.lpg.presentation.admin.products.domain.repository

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.products.domain.model.AdminProductModel
import kotlinx.coroutines.flow.Flow

interface AdminProductRepository {
    fun getProducts(): Flow<Response<List<AdminProductModel>>>
}