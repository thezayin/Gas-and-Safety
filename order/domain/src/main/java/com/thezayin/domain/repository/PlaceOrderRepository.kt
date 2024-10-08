package com.thezayin.domain.repository

import com.thezayin.databases.model.CartModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface PlaceOrderRepository {
    fun placeOrder(
        userID: String,
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String?,
        message: String?,
        orderDate: String,
        orderTime: String,
        orderDateTime: String,
        orderStatus: String,
        paymentMethod: String,
        totalAmount: String,
        orders: List<CartModel>
    ): Flow<Response<Boolean>>
}