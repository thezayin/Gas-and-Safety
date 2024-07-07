package com.thezayin.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetMyOrders :
    suspend (String) -> Flow<Response<List<com.thezayin.entities.OrderHistoryModel>>>

class GetMyOrdersImpl(private val repository: com.thezayin.domain.MyOrdersRepository) :
    GetMyOrders {
    override suspend fun invoke(userId: String): Flow<Response<List<com.thezayin.entities.OrderHistoryModel>>> =
        repository.getMyOrders(userId)
}