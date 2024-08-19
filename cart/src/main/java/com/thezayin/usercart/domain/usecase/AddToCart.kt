package com.thezayin.usercart.domain.usecase

import com.thezayin.entities.HomeProductsModel
import com.thezayin.framework.utils.Response
import com.thezayin.usercart.domain.repository.CartProRepository
import kotlinx.coroutines.flow.Flow

interface AddToCart : suspend (HomeProductsModel) -> Flow<Response<Boolean>>

class AddToCartImpl(private val cartProRepository: CartProRepository) :
    AddToCart {
    override suspend fun invoke(product: HomeProductsModel): Flow<Response<Boolean>> =
        cartProRepository.addToCart(product)
}