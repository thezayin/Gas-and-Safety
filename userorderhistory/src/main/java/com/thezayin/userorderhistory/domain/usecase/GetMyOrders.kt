package com.thezayin.userorderhistory.domain.usecase

import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.userorderhistory.domain.repository.MyOrdersRepository
import kotlinx.coroutines.flow.Flow

interface GetMyOrders :
    suspend (String) -> Flow<Response<List<OrderModel>>>

class GetMyOrdersImpl(private val repository: MyOrdersRepository) :
    GetMyOrders {
    override suspend fun invoke(userId: String): Flow<Response<List<OrderModel>>> =
        repository.getMyOrders(userId)
}