package com.thezayin.userbuy.domain.repository

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface PlaceOrderRepository {
    fun placeOrder(
        userID: String,
        name: String,
        phoneNumber: String,
        email: String?,
        address: String,
        message: String?,
        orderDate: String,
        orderTime: String,
        orderDateTime: String,
        orderStatus: String,
        paymentMethod: String,
        totalAmount: String,
        orders: List<com.thezayin.entities.CartModel>
    ): Flow<Response<Boolean>>
}