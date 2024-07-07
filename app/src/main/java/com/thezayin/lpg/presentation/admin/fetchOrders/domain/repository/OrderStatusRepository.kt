package com.thezayin.lpg.presentation.admin.fetchOrders.domain.repository

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.model.OrderStatusModel
import kotlinx.coroutines.flow.Flow

interface OrderStatusRepository {
    fun getStatus(): Flow<Response<List<OrderStatusModel>>>
    fun updateStatus(status: String): Flow<Response<Boolean>>
}