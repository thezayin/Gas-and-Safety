package com.thezayin.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface DeleteAllCart : suspend () -> Flow<Response<Boolean>>
class DeleteAllCartImpl(private val cartProRepository: com.thezayin.domain.CartProRepository) :
    DeleteAllCart {
    override suspend fun invoke(): Flow<Response<Boolean>> =
        cartProRepository.deleteAllFromCart()
}