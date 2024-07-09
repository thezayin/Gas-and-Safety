package com.thezayin.usercart.domain.repository

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface CartProRepository {
    fun getAllProduct(): Flow<Response<List<com.thezayin.entities.CartModel>>>
    fun addToCart(product: com.thezayin.entities.HomeProductsModel): Flow<Response<Boolean>>
    fun updateQuantity(id: String, quantity: Int, totalPrice: Int): Flow<Response<Boolean>>
    fun deleteFromCart(id: String): Flow<Response<Boolean>>
    fun deleteAllFromCart(): Flow<Response<Boolean>>
}