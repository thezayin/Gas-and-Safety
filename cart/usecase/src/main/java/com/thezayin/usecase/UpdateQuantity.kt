package com.thezayin.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface UpdateQuantity : suspend (String, Int, Int) -> Flow<Response<Boolean>>

class UpdateQuantityImpl(private val cartProRepository: com.thezayin.domain.CartProRepository) :
    UpdateQuantity {
    override suspend fun invoke(id: String, quantity: Int, price: Int): Flow<Response<Boolean>> =
        cartProRepository.updateQuantity(id, quantity, price)
}