package com.thezayin.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetCartProducts : suspend () -> Flow<Response<List<com.thezayin.entities.CartModel>>>

class GetCartProductsImpl(private val cartProRepository: com.thezayin.domain.CartProRepository) :
    GetCartProducts {
    override suspend fun invoke(): Flow<Response<List<com.thezayin.entities.CartModel>>> =
        cartProRepository.getAllProduct()
}