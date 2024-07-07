package com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.model.OrderStatusModel
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.repository.OrderStatusRepository
import kotlinx.coroutines.flow.Flow

interface GetStatusList:suspend () -> Flow<Response<List<OrderStatusModel>>>

class GetStatusListImpl ( private val repository: OrderStatusRepository) : GetStatusList {
    override suspend fun invoke(): Flow<Response<List<OrderStatusModel>>> =
        repository.getStatus()
}