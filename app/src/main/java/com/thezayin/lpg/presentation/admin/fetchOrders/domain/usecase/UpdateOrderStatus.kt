package com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.repository.OrderStatusRepository
import kotlinx.coroutines.flow.Flow

interface UpdateOrderStatus : suspend (String) -> Flow<Response<Boolean>>

class UpdateOrderStatusImpl(private val repository: OrderStatusRepository) : UpdateOrderStatus {
    override suspend fun invoke(status: String): Flow<Response<Boolean>> =
        repository.updateStatus(status)
}