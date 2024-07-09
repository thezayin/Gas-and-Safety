package com.thezayin.usercart.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.usercart.domain.repository.CartProRepository
import kotlinx.coroutines.flow.Flow

interface DeleteAllCart : suspend () -> Flow<Response<Boolean>>
class DeleteAllCartImpl(private val cartProRepository: CartProRepository) :
    DeleteAllCart {
    override suspend fun invoke(): Flow<Response<Boolean>> =
        cartProRepository.deleteAllFromCart()
}