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
 * Use case interface for fetching products along with their associated images.
 *
 * This interface defines the contract for retrieving a list of products with images
 * from a data source, wrapped in a Resource type to handle success and error states.
 */
interface FetchProductsWithImagesUseCase :
    suspend (List<HomeProdModel>) -> Flow<Resource<List<HomeProdModel>>>


/**
 * Implementation of the [FetchProductsWithImagesUseCase] interface.
 *
 * This class handles fetching products along with their images by interacting with the [ProductRepository].
 *
 * @property repository The repository responsible for product data operations.
 */
class FetchProductsWithImagesUseCaseImpl(
    private val repository: ProductRepository
) : FetchProductsWithImagesUseCase {

    /**
     * Executes the use case to retrieve products with images from the repository.
     *
     * @param products A list of [HomeProdModel] for which images are to be fetched.
     * @return A Flow emitting Resource<List<HomeProdModel>> containing the list of products with images or an error state.
     */
    override suspend fun invoke(products: List<HomeProdModel>): Flow<Resource<List<HomeProdModel>>> =
        flow {
            emit(Resource.Loading) // Indicate that the operation has started
            try {
                // Fetch products with images from the repository
                val responseFlow = repository.fetchProductsWithImages(products)
                emitAll(responseFlow) // Emit the response from the repository
            } catch (e: Exception) {
                // Emit an error response if the fetch fails
                emit(Resource.Error("Failed to fetch products with images: ${e.localizedMessage}"))
            }
        }.flowOn(Dispatchers.IO) // Perform the operation on the IO dispatcher
}