package com.thezayin.domain.usecase

import com.thezayin.domain.model.HomeProdModel
import com.thezayin.domain.repository.ProductRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Use case interface for fetching products.
 *
 * This interface defines the contract for retrieving a list of products
 * from a data source, wrapped in a Resource type to handle success and error states.
 */
interface FetchProductsUseCase {
    /**
     * Executes the use case to retrieve a list of products.
     *
     * @return A Flow emitting Resource<List<HomeProdModel>> containing the list
     *         of products or an error state.
     */
    suspend operator fun invoke(): Flow<Resource<List<HomeProdModel>>>
}

/**
 * Implementation of the [FetchProductsUseCase] interface.
 *
 * This class interacts with the [ProductRepository] to fetch products.
 *
 * @property repository The repository responsible for product data operations.
 */
class FetchProductsUseCaseImpl(
    private val repository: ProductRepository
) : FetchProductsUseCase {
    /**
     * Retrieves the products from the repository.
     *
     * @return A Flow emitting Resource<List<HomeProdModel>> containing the list
     *         of products or an error state.
     */
    override suspend fun invoke(): Flow<Resource<List<HomeProdModel>>> = flow {
        emit(Resource.Loading) // Indicate that the operation has started
        try {
            // Fetch products from the repository
            val responseFlow = repository.fetchProducts()
            emitAll(responseFlow) // Emit the response from the repository
        } catch (e: Exception) {
            // Emit an error response if the fetch fails
            emit(Resource.Error("Failed to fetch products: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO) // Perform the operation on the IO dispatcher
}