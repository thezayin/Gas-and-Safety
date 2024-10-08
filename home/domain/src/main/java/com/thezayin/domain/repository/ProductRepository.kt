package com.thezayin.domain.repository

import com.thezayin.domain.model.HomeProdModel
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing product-related data operations.
 *
 * This interface defines the contract for fetching products,
 * including products with images. It provides methods to retrieve
 * lists of products wrapped in a Resource type, which can represent
 * success or error states.
 */
interface ProductRepository {

    /**
     * Retrieves a list of products.
     *
     * This method fetches all products from the data source and emits
     * a Flow of Resource containing a list of HomeProdModel. The Resource
     * wrapper is used to manage loading, success, and error states.
     *
     * @return A Flow emitting Resource<List<HomeProdModel>> containing
     *         the list of products or an error state.
     */
    fun fetchProducts(): Flow<Resource<List<HomeProdModel>>>

    /**
     * Retrieves products along with their associated images.
     *
     * This method takes a list of HomeProdModel and fetches additional
     * image data for each product, returning a Flow of Resource containing
     * the enriched list of products.
     *
     * @param products A list of HomeProdModel representing the products
     *                 for which images are to be fetched.
     * @return A Flow emitting Resource<List<HomeProdModel>> containing
     *         the list of products with images or an error state.
     */
    fun fetchProductsWithImages(products: List<HomeProdModel>): Flow<Resource<List<HomeProdModel>>>
}