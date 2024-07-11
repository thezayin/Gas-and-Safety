package com.thezayin.adminorders.domain.repository

import com.thezayin.entities.OrderStatusModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface OrderStatusRepository {
    fun getStatus(): Flow<Response<List<OrderStatusModel>>>
    fun updateStatus(status: String): Flow<Response<Boolean>>
}