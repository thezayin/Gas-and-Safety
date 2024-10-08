package com.thezayin.domain.usecase

import com.thezayin.databases.model.CartModel
import com.thezayin.domain.repository.CartRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving all products in the cart.
 *
 * This interface defines the contract for fetching all cart products,
 * and the implementation will interact with the repository to retrieve the data.
 */
interface FetchCartProducts : suspend () -> Flow<Resource<List<CartModel>>>

/**
 * Implementation of the [FetchCartProducts] interface.
 *
 * This class interacts with the [CartRepository] to retrieve all products in the cart.
 *
 * @property cartRepository The repository responsible for managing cart operations.
 */
class FetchCartProductsImpl(private val cartRepository: CartRepository) : FetchCartProducts {

    /**
     * Executes the use case to fetch all products in the cart.
     *
     * @return A [Flow] emitting a [Resource] containing the list of [CartModel]s.
     */
    override suspend fun invoke(): Flow<Resource<List<CartModel>>> =
        cartRepository.getAllCartItems()
}