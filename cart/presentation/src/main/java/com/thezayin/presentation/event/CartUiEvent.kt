package com.thezayin.presentation.event

import com.thezayin.databases.model.CartModel

/**
 * Sealed interface representing all possible UI events related to the cart.
 * These events help manage the cart state and handle actions such as loading, errors, adding, or removing products.
 */
sealed interface CartUiEvent {

    /**
     * Event to indicate the loading state of a cart operation.
     * @property isLoading Boolean flag to indicate if loading is in progress.
     */
    data class Loading(val isLoading: Boolean) : CartUiEvent

    /**
     * Event to display an error state.
     * @property isError Boolean flag to indicate if an error occurred.
     */
    data class Error(val isError: Boolean) : CartUiEvent

    /**
     * Event to provide an error message for display.
     * @property message The error message to display.
     */
    data class ErrorMessage(val message: String) : CartUiEvent

    /**
     * Event to indicate that a product has been successfully added to the cart.
     * @property isAdded Boolean flag to indicate success.
     */
    data class ProductAdded(val isAdded: Boolean) : CartUiEvent

    /**
     * Event to indicate that a product has been successfully removed from the cart.
     * @property isRemoved Boolean flag to indicate success.
     */
    data class ProductRemoved(val isRemoved: Boolean) : CartUiEvent

    /**
     * Event to provide the list of products currently in the cart.
     * @property products The list of [CartModel] representing the products in the cart.
     */
    data class CartProducts(val products: List<CartModel>) : CartUiEvent
}