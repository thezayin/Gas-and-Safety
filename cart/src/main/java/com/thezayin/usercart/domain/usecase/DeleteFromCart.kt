package com.thezayin.usercart.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.usercart.domain.repository.CartProRepository
import kotlinx.coroutines.flow.Flow

interface DeleteFromCart : suspend (String) -> Flow<Response<Boolean>>

class DeleteFromCartImpl(private val cartProRepository: CartProRepository) :
    DeleteFromCart {
    override suspend fun invoke(id: String): Flow<Response<Boolean>> =
        cartProRepository.deleteFromCart(id)
}