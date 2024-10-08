package com.thezayin.domain.repository

import com.thezayin.domain.model.OrderModel
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MyOrdersRepository {
    fun getMyOrders(userId: String): Flow<Resource<List<OrderModel>>>
}