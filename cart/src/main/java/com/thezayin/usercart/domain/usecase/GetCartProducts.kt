package com.thezayin.usercart.domain.usecase

import com.thezayin.entities.CartModel
import com.thezayin.framework.utils.Response
import com.thezayin.usercart.domain.repository.CartProRepository
import kotlinx.coroutines.flow.Flow

interface GetCartProducts : suspend () -> Flow<Response<List<CartModel>>>

class GetCartProductsImpl(private val cartProRepository: CartProRepository) :
    GetCartProducts {
    override suspend fun invoke(): Flow<Response<List<CartModel>>> =
        cartProRepository.getAllProduct()
}