package com.thezayin.domain

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface MyOrdersRepository {
    fun getMyOrders(userId: String): Flow<Response<List<com.thezayin.entities.OrderHistoryModel>>>
}