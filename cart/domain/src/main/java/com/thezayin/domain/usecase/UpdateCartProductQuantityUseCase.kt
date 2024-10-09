package com.thezayin.domain.usecase

import com.thezayin.domain.repository.CartRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case for updating the quantity and total price of a specific product in the cart.
 *
 * This interface defines the contract for updating the quantity of a product in the cart.
 */
interface UpdateCartProductQuantityUseCase : suspend (UpdateCartQuantityParams) -> Flow<Resource<Boolean>>

/**
 * Data class representing the parameters required to update the product quantity in the cart.
 *
 * @property id The unique identifier of the product.
 * @property quantity The updated quantity of the product in the cart.
 * @property totalPrice The updated total price for the specified quantity of the product.
 */
data class UpdateCartQuantityParams(
    val id: String,
    val quantity: Int,
    val totalPrice: Int
)

/**
 * Implementation of the [UpdateCartProductQuantityUseCase] interface.
 *
 * This class interacts with the [CartRepository] to update the quantity and total price of a product in the cart.
 *
 * @property cartRepository The repository responsible for managing cart operations.
 */
class UpdateCartProductQuantityUseCaseImpl(private val cartRepository: CartRepository) : UpdateCartProductQuantityUseCase {

    /**
     * Executes the use case to update the quantity and total price of a product in the cart.
     *
     * @param params The parameters encapsulated in [UpdateCartQuantityParams].
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    override suspend fun invoke(params: UpdateCartQuantityParams): Flow<Resource<Boolean>> {
        return cartRepository.updateProductQuantity(
            id = params.id,
            quantity = params.quantity,
            totalPrice = params.totalPrice
        )
    }
}