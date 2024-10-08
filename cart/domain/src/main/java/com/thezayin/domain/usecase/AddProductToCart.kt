package com.thezayin.domain.usecase

import com.thezayin.domain.repository.CartRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case for adding a product to the cart.
 *
 * This interface defines the contract for adding a product to the cart,
 * and the implementation will handle the interaction with the repository.
 */
interface AddProductToCart : suspend (AddToCartParams) -> Flow<Resource<Boolean>>

/**
 * Data class representing the parameters required to add a product to the cart.
 *
 * @property id The unique identifier of the product.
 * @property name The name of the product.
 * @property price The price of the product.
 * @property description The description of the product.
 * @property imageUri The URI of the product image.
 */
data class AddToCartParams(
    val id: String,
    val name: String,
    val price: String,
    val description: String,
    val imageUri: String
)

/**
 * Implementation of the [AddProductToCart] interface.
 *
 * This class interacts with the [CartRepository] to add a product to the cart.
 *
 * @property cartRepository The repository responsible for managing cart operations.
 */
class AddProductToCartImpl(private val cartRepository: CartRepository) : AddProductToCart {

    /**
     * Executes the use case to add a product to the cart.
     *
     * @param params The parameters encapsulated in [AddToCartParams].
     * @return A [Flow] emitting a [Resource] indicating success or failure of the operation.
     */
    override suspend fun invoke(params: AddToCartParams): Flow<Resource<Boolean>> {
        return cartRepository.addProductToCart(
            id = params.id,
            name = params.name,
            price = params.price,
            description = params.description,
            imageUri = params.imageUri
        )
    }
}