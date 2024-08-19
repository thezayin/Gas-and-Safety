package com.thezayin.usercart.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.usercart.domain.repository.CartProRepository
import kotlinx.coroutines.flow.Flow

interface UpdateQuantity : suspend (String, Int, Int) -> Flow<Response<Boolean>>

class UpdateQuantityImpl(private val cartProRepository: CartProRepository) :
    UpdateQuantity {
    override suspend fun invoke(id: String, quantity: Int, price: Int): Flow<Response<Boolean>> =
        cartProRepository.updateQuantity(id, quantity, price)
}