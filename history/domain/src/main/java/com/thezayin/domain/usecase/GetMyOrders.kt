package com.thezayin.domain.usecase

import com.thezayin.domain.model.OrderModel
import com.thezayin.domain.repository.MyOrdersRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetMyOrders : suspend (String) -> Flow<Response<List<OrderModel>>>

class GetMyOrdersImpl(private val repository: MyOrdersRepository) : GetMyOrders {
    override suspend fun invoke(userId: String): Flow<Response<List<OrderModel>>> =
        repository.getMyOrders(userId)
}