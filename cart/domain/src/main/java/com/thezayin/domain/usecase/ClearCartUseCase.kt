package com.thezayin.domain.usecase

import com.thezayin.domain.repository.CartRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case for deleting all items from the cart.
 *
 * This interface defines the contract for clearing the cart, removing all products.
 */
interface ClearCartUseCase : suspend () -> Flow<Resource<Boolean>>

/**
 * Implementation of the [ClearCartUseCase] interface.
 *
 * This class interacts with the [CartRepository] to clear all items from the cart.
 *
 * @property cartRepository The repository responsible for managing cart operations.
 */
class ClearCartUseCaseImpl(private val cartRepository: CartRepository) : ClearCartUseCase {

    /**
     * Executes the use case to clear all products from the cart.
     *
     * @return A [Flow] emitting a [Resource] indicating success or failure of the operation.
     */
    override suspend fun invoke(): Flow<Resource<Boolean>> {
        return cartRepository.clearCart()
    }
}