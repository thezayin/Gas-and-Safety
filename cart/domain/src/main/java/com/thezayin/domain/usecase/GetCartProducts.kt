package com.thezayin.domain.usecase

import com.thezayin.databases.model.CartModel
import com.thezayin.domain.repository.CartProRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetCartProducts : suspend () -> Flow<Response<List<CartModel>>>

class GetCartProductsImpl(private val cartProRepository: CartProRepository) :
    GetCartProducts {
    override suspend fun invoke(): Flow<Response<List<CartModel>>> =
        cartProRepository.getAllProduct()
}