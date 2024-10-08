package com.thezayin.domain.usecase

import com.thezayin.domain.repository.CartProRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface UpdateQuantity : suspend (String, Int, Int) -> Flow<Response<Boolean>>

class UpdateQuantityImpl(private val cartProRepository: CartProRepository) :
    UpdateQuantity {
    override suspend fun invoke(id: String, quantity: Int, price: Int): Flow<Response<Boolean>> =
        cartProRepository.updateQuantity(id, quantity, price)
}