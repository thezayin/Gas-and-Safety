package com.thezayin.domain.usecase

import com.thezayin.domain.repository.CartRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case for deleting a specific product from the cart by its unique identifier.
 *
 * This interface defines the contract for removing a product from the cart.
 */
interface RemoveProductFromCartUseCase : suspend (RemoveFromCartParams) -> Flow<Resource<Boolean>>

/**
 * Data class representing the parameters required to delete a product from the cart.
 *
 * @property id The unique identifier of the product to be removed from the cart.
 */
data class RemoveFromCartParams(
    val id: String
)

/**
 * Implementation of the [RemoveProductFromCartUseCase] interface.
 *
 * This class interacts with the [CartRepository] to remove a product from the cart.
 *
 * @property cartRepository The repository responsible for managing cart operations.
 */
class RemoveProductFromCartUseCaseImpl(private val cartRepository: CartRepository) : RemoveProductFromCartUseCase {

    /**
     * Executes the use case to remove a product from the cart.
     *
     * @param params The parameters encapsulated in [RemoveFromCartParams].
     * @return A [Flow] emitting a [Resource] indicating success or failure of the operation.
     */
    override suspend fun invoke(params: RemoveFromCartParams): Flow<Resource<Boolean>> {
        return cartRepository.removeProductFromCart(params.id)
    }
}