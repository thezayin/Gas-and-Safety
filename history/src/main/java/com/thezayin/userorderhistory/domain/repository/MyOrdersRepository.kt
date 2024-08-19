package com.thezayin.userorderhistory.domain.repository

import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface MyOrdersRepository {
    fun getMyOrders(userId: String): Flow<Response<List<OrderModel>>>
}