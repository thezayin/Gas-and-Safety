package com.thezayin.domain.repository

import com.thezayin.domain.model.OrderModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface MyOrdersRepository {
    fun getMyOrders(userId: String): Flow<Response<List<OrderModel>>>
}