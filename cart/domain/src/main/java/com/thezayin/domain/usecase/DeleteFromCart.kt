package com.thezayin.domain.usecase

import com.thezayin.domain.repository.CartProRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface DeleteFromCart : suspend (String) -> Flow<Response<Boolean>>

class DeleteFromCartImpl(private val cartProRepository: CartProRepository) :
    DeleteFromCart {
    override suspend fun invoke(id: String): Flow<Response<Boolean>> =
        cartProRepository.deleteFromCart(id)
}