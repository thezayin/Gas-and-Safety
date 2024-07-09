package com.thezayin.userorderhistory.domain.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetMyOrders :
    suspend (String) -> Flow<Response<List<com.thezayin.entities.OrderModel>>>

class GetMyOrdersImpl(private val repository: com.thezayin.userorderhistory.domain.repository.MyOrdersRepository) :
    GetMyOrders {
    override suspend fun invoke(userId: String): Flow<Response<List<com.thezayin.entities.OrderModel>>> =
        repository.getMyOrders(userId)
}