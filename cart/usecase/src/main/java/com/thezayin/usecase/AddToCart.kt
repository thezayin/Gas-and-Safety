package com.thezayin.usecase

import com.thezayin.domain.CartProRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface AddToCart : suspend (com.thezayin.entities.HomeProductsModel) -> Flow<Response<Boolean>>

class AddToCartImpl(private val cartProRepository: CartProRepository) :
    AddToCart {
    override suspend fun invoke(product: com.thezayin.entities.HomeProductsModel): Flow<Response<Boolean>> =
        cartProRepository.addToCart(product)
}