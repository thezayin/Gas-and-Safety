package com.thezayin.lpg.presentation.admin.fetchOrders.domain.repository

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.model.FetchOrdersModel
import kotlinx.coroutines.flow.Flow

interface FetchOrdersRepository {
    fun getOrdersRepository(): Flow<Response<List<FetchOrdersModel>>>
}