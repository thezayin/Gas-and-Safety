package com.thezayin.domain.usecase

import com.thezayin.domain.repository.CartProRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface AddToCart : suspend (String, String, String, String, String) -> Flow<Response<Boolean>>

class AddToCartImpl(private val cartProRepository: CartProRepository) :
    AddToCart {
    override suspend fun invoke(
        id: String,
        name: String,
        price: String,
        description: String,
        imageUri: String
    ): Flow<Response<Boolean>> =
        cartProRepository.addToCart(
            id,
            name,
            price,
            description,
            imageUri
        )
}