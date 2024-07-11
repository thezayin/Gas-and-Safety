package com.thezayin.usercart.domain.repository

import com.thezayin.entities.CartModel
import com.thezayin.entities.HomeProductsModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface CartProRepository {
    fun getAllProduct(): Flow<Response<List<CartModel>>>
    fun addToCart(product: HomeProductsModel): Flow<Response<Boolean>>
    fun updateQuantity(id: String, quantity: Int, totalPrice: Int): Flow<Response<Boolean>>
    fun deleteFromCart(id: String): Flow<Response<Boolean>>
    fun deleteAllFromCart(): Flow<Response<Boolean>>
}