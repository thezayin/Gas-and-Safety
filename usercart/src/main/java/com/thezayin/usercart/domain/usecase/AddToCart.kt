package com.thezayin.usercart.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.usercart.domain.repository.CartProRepository
import kotlinx.coroutines.flow.Flow

interface AddToCart : suspend (com.thezayin.entities.HomeProductsModel) -> Flow<Response<Boolean>>

class AddToCartImpl(private val cartProRepository: CartProRepository) :
    AddToCart {
    override suspend fun invoke(product: com.thezayin.entities.HomeProductsModel): Flow<Response<Boolean>> =
        cartProRepository.addToCart(product)
}