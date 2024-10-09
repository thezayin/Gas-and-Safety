package com.thezayin.presentation.state

import com.thezayin.databases.model.CartModel

/**
 * Data class representing the UI state for cart-related operations.
 * This class maintains the state of the cart screen, such as loading, product lists, and error handling.
 *
 * @property isLoading Boolean flag indicating if a cart operation is in progress.
 * @property cartProducts List of products currently in the cart.
 * @property isError Boolean flag indicating if an error has occurred.
 * @property errorMessage Message to be displayed in case of an error.
 * @property isProductAdded Boolean flag indicating if a product was successfully added to the cart.
 * @property isProductRemoved Boolean flag indicating if a product was successfully removed from the cart.
 */
data class CartUiState(
    val isLoading: Boolean = false,
    val cartProducts: List<CartModel>? = null,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isProductAdded: Boolean = false,
    val isProductRemoved: Boolean = false
)