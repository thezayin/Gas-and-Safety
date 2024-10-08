package com.thezayin.domain.usecase

import com.thezayin.domain.repository.CartProRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface DeleteAllCart : suspend () -> Flow<Response<Boolean>>
class DeleteAllCartImpl(private val cartProRepository: CartProRepository) :
    DeleteAllCart {
    override suspend fun invoke(): Flow<Response<Boolean>> =
        cartProRepository.deleteAllFromCart()
}