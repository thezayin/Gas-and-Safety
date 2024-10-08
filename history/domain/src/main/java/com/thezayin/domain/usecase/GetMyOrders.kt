package com.thezayin.domain.usecase

import com.thezayin.domain.model.OrderModel
import com.thezayin.domain.repository.MyOrdersRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetMyOrders : suspend (String) -> Flow<Resource<List<OrderModel>>>

class GetMyOrdersImpl(private val repository: MyOrdersRepository) : GetMyOrders {
    override suspend fun invoke(userId: String): Flow<Resource<List<OrderModel>>> =
        repository.getMyOrders(userId)
}