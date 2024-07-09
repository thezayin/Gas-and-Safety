package com.thezayin.adminorders.domain.repository

import com.thezayin.framework.utils.Response
import com.thezayin.entities.OrderModel
import kotlinx.coroutines.flow.Flow

interface FetchOrdersRepository {
    fun getOrdersRepository(): Flow<Response<List<OrderModel>>>
}