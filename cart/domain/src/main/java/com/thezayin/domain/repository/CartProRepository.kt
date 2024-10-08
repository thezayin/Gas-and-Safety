package com.thezayin.domain.repository

import com.thezayin.databases.model.CartModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface CartProRepository {
    fun getAllProduct(): Flow<Response<List<CartModel>>>
    fun addToCart(
        id: String,
        name: String,
        price: String,
        description: String,
        imageUri: String
    ): Flow<Response<Boolean>>

    fun updateQuantity(id: String, quantity: Int, totalPrice: Int): Flow<Response<Boolean>>
    fun deleteFromCart(id: String): Flow<Response<Boolean>>
    fun deleteAllFromCart(): Flow<Response<Boolean>>
}