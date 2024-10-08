package com.thezayin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.analytics.analytics.Analytics
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.databases.model.CartModel
import com.thezayin.domain.usecase.ClearCartUseCase
import com.thezayin.domain.usecase.FetchCartProducts
import com.thezayin.domain.usecase.RemoveFromCartParams
import com.thezayin.domain.usecase.RemoveProductFromCartUseCase
import com.thezayin.domain.usecase.UpdateCartProductQuantityUseCase
import com.thezayin.domain.usecase.UpdateCartQuantityParams
import com.thezayin.framework.utils.Resource
import com.thezayin.presentation.event.CartUiEvent
import com.thezayin.presentation.state.CartUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    val analytics: Analytics,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase,
    private val clearCartUseCase: ClearCartUseCase,
    private val fetchCartProducts: FetchCartProducts,
    private val updateCartProductQuantityUseCase: UpdateCartProductQuantityUseCase
) : ViewModel() {

    private val _cartUiState = MutableStateFlow(CartUiState())
    val cartUiState = _cartUiState.asStateFlow()

    init {
        fetchAllProducts()
    }

    /**
     * Handles UI events by updating the CartUiState.
     *
     * @param event The UI event to handle.
     */
    private fun handleCartUiEvent(event: CartUiEvent) {
        when (event) {
            is CartUiEvent.Error -> _cartUiState.update { it.copy(isError = event.isError) }
            is CartUiEvent.CartProducts -> _cartUiState.update { it.copy(cartProducts = event.products) }
            is CartUiEvent.Loading -> _cartUiState.update { it.copy(isLoading = event.isLoading) }
            is CartUiEvent.ProductAdded -> _cartUiState.update { it.copy(isProductAdded = event.isAdded) }
            is CartUiEvent.ErrorMessage -> _cartUiState.update { it.copy(errorMessage = event.message) }
            is CartUiEvent.ProductRemoved -> _cartUiState.update { it.copy(isProductRemoved = event.isRemoved) }
        }
    }

    // Convenience methods to emit specific UI events
    private fun showLoading(loading: Boolean) = handleCartUiEvent(CartUiEvent.Loading(loading))
    private fun getProducts(list: List<CartModel>) =
        handleCartUiEvent(CartUiEvent.CartProducts(list))

    private fun errorMessage(message: String) = handleCartUiEvent(CartUiEvent.ErrorMessage(message))
    fun showError(error: Boolean) = handleCartUiEvent(CartUiEvent.Error(error))
    fun removedFromCart(isRemoved: Boolean) =
        handleCartUiEvent(CartUiEvent.ProductRemoved(isRemoved))

    /**
     * Fetches all products from the cart using the FetchCartProducts use case.
     */
    private fun fetchAllProducts() = viewModelScope.launch {
        fetchCartProducts().collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    getProducts(response.data)
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }

    /**
     * Clears all items from the cart.
     */
    fun clearCart() = viewModelScope.launch {
        clearCartUseCase().collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    removedFromCart(true)
                    analytics.logEvent(AnalyticsEvent.CartClearedEvent()) // Log cart cleared event
                    fetchAllProducts() // Refresh the cart after clearing
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }

    /**
     * Removes a product from the cart.
     *
     * @param product The product to be removed.
     */
    fun removeProduct(product: CartModel) = viewModelScope.launch {
        removeProductFromCartUseCase(RemoveFromCartParams(product.localId.toString())).collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    removedFromCart(true)
                    analytics.logEvent(AnalyticsEvent.CartItemRemovedEvent(product.localId.toString())) // Log item removed event
                    fetchAllProducts() // Refresh the cart after removal
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }

    /**
     * Increments the quantity of a specific product in the cart.
     *
     * @param product The product whose quantity needs to be incremented.
     */
    fun incrementQuantity(product: CartModel) = viewModelScope.launch {
        val newQuantity = product.quantity + 1
        val newTotalPrice = product.totalPrice + product.price
        analytics.logEvent(
            AnalyticsEvent.CartItemAddedEvent(
                product.localId.toString(),
                newQuantity
            )
        ) // Log item added event
        updateProductQuantity(product.localId.toString(), newQuantity, newTotalPrice.toInt())
    }

    /**
     * Decrements the quantity of a specific product in the cart.
     * If the quantity reaches zero, the product is removed from the cart.
     *
     * @param product The product whose quantity needs to be decremented.
     */
    fun decrementQuantity(product: CartModel) {
        if (product.quantity == 1) {
            removeProduct(product)
            return
        }
        val newQuantity = product.quantity - 1
        val newTotalPrice = product.totalPrice - product.price
        updateProductQuantity(product.localId.toString(), newQuantity, newTotalPrice.toInt())
    }

    /**
     * Updates the quantity and total price of a specific product in the cart.
     *
     * @param id The unique identifier of the product.
     * @param qty The new quantity of the product.
     * @param price The new total price of the product.
     */
    private fun updateProductQuantity(id: String, qty: Int, price: Int) = viewModelScope.launch {
        val params = UpdateCartQuantityParams(id, qty, price)
        updateCartProductQuantityUseCase(params).collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    fetchAllProducts()
                }// Refresh the cart after updating
                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> {
                    showLoading(true)
                }
            }
        }
    }
}
