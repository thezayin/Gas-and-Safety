package com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.model.FetchOrdersModel
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.repository.FetchOrdersRepository
import kotlinx.coroutines.flow.Flow

interface FetchOrders :
    suspend () -> Flow<Response<List<FetchOrdersModel>>>

class FetchOrdersImpl(private val repository: FetchOrdersRepository) : FetchOrders {
    override suspend fun invoke(): Flow<Response<List<FetchOrdersModel>>> =
        repository.getOrdersRepository()
}