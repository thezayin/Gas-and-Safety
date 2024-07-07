package com.thezayin.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface DeleteFromCart : suspend (String) -> Flow<Response<Boolean>>

class DeleteFromCartImpl(private val cartProRepository: com.thezayin.domain.CartProRepository) :
    DeleteFromCart {
    override suspend fun invoke(id: String): Flow<Response<Boolean>> =
        cartProRepository.deleteFromCart(id)
}