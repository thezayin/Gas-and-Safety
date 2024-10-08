package com.thezayin.domain.repository

import com.thezayin.databases.model.CartModel
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Interface for managing cart-related operations.
 * This repository defines the contract for performing CRUD operations on the cart.
 * The implementation will handle data interactions, such as fetching, adding, updating, and deleting items in the cart.
 */
interface CartRepository {

    /**
     * Retrieves all products in the cart.
     *
     * @return A [Flow] emitting a [Resource] containing a list of [CartModel] representing all items in the cart.
     */
    fun getAllCartItems(): Flow<Resource<List<CartModel>>>

    /**
     * Adds a new product to the cart.
     * If a product with the same ID exists, it will be updated.
     *
     * @param id The unique identifier for the product.
     * @param name The name of the product.
     * @param price The price of the product as a String.
     * @param description A brief description of the product.
     * @param imageUri The URI of the product's image.
     *
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    fun addProductToCart(
        id: String,
        name: String,
        price: String,
        description: String,
        imageUri: String
    ): Flow<Resource<Boolean>>

    /**
     * Updates the quantity and total price of an existing product in the cart.
     *
     * @param id The unique identifier of the product to update.
     * @param quantity The new quantity of the product.
     * @param totalPrice The updated total price, based on the new quantity.
     *
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    fun updateProductQuantity(
        id: String,
        quantity: Int,
        totalPrice: Int
    ): Flow<Resource<Boolean>>

    /**
     * Deletes a specific product from the cart by its ID.
     *
     * @param id The unique identifier of the product to remove.
     *
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    fun removeProductFromCart(id: String): Flow<Resource<Boolean>>

    /**
     * Clears all products from the cart.
     *
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    fun clearCart(): Flow<Resource<Boolean>>
}