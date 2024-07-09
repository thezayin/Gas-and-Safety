package com.thezayin.userbuy.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.userbuy.domain.repository.PlaceOrderRepository
import kotlinx.coroutines.flow.Flow

interface PlaceOrder : suspend (
    String, String, String, String?, String, String?, String, String, String, String, String, String, List<com.thezayin.entities.CartModel>
) -> Flow<Response<Boolean>>

class PlaceOrderImpl(private val placeOrderRepository: PlaceOrderRepository) :
    PlaceOrder {
    override suspend fun invoke(
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
    ): Flow<Response<Boolean>> =
        placeOrderRepository.placeOrder(
            userID = userID,
            name = name,
            phoneNumber = phoneNumber,
            email = email,
            address = address,
            message = message,
            orderDate = orderDate,
            orderTime = orderTime,
            orderDateTime = orderDateTime,
            orderStatus = orderStatus,
            paymentMethod = paymentMethod,
            totalAmount = totalAmount, orders = orders
        )
}