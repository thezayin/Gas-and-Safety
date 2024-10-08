package com.thezayin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.databases.model.CartModel
import com.thezayin.domain.usecase.DeleteAllCart
import com.thezayin.domain.usecase.DeleteFromCart
import com.thezayin.domain.usecase.GetCartProducts
import com.thezayin.domain.usecase.UpdateQuantity
import com.thezayin.framework.utils.Response
import com.thezayin.presentation.event.CartUiEvents
import com.thezayin.presentation.state.CartUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val deleteFromCart: DeleteFromCart,
    private val deleteAllCart: DeleteAllCart,
    private val getCartProducts: GetCartProducts,
    private val updateQuantity: UpdateQuantity
) : ViewModel() {
    private val _cartUiState = MutableStateFlow(CartUiState())
    val cartUiState = _cartUiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun cartUiEvents(events: CartUiEvents) {
        when (events) {
            is CartUiEvents.ShowError -> _cartUiState.update { it.copy(isError = events.isError) }
            is CartUiEvents.GetProducts -> _cartUiState.update { it.copy(getProducts = events.list) }
            is CartUiEvents.ShowLoading -> _cartUiState.update { it.copy(isLoading = events.isLoading) }
            is CartUiEvents.AddedToCart -> _cartUiState.update { it.copy(productAdded = events.isAdded) }
            is CartUiEvents.ErrorMessage -> _cartUiState.update { it.copy(errorMessage = events.message) }
            is CartUiEvents.RemovedFromCart -> _cartUiState.update { it.copy(productRemoved = events.isRemoved) }
        }
    }

    private fun showLoading(isLoading: Boolean) = cartUiEvents(CartUiEvents.ShowLoading(isLoading))
    fun removedFromCart(isRemoved: Boolean) = cartUiEvents(CartUiEvents.RemovedFromCart(isRemoved))
    private fun getProducts(list: List<CartModel>) = cartUiEvents(CartUiEvents.GetProducts(list))
    private fun errorMessage(message: String) = cartUiEvents(CartUiEvents.ErrorMessage(message))
    fun showError(isError: Boolean) = cartUiEvents(CartUiEvents.ShowError(isError))

    private fun getAllProducts() = viewModelScope.launch {
        getCartProducts().collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    getProducts(response.data)
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    private fun updateProductQuantity(id: String, qty: Int, price: Int) = viewModelScope.launch {
        updateQuantity(id, qty, price).collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    getAllProducts()
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    fun emptyCart() = viewModelScope.launch {
        deleteAllCart().collect { response ->
            when (response) {
                is Response.Success -> {
                    removedFromCart(response.data)
                    showLoading(false)
                    getAllProducts()
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    private fun deleteProduct(product: CartModel) = viewModelScope.launch {
        deleteFromCart(product.id!!).collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    getAllProducts()
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    fun incrementQuantity(product: CartModel) = viewModelScope.launch {
        val quantity = product.quantity!! + 1
        val totalPrice = product.totalPrice!!.toInt() + product.price!!.toInt()
        val id = product.id!!
        updateProductQuantity(id, quantity, totalPrice)
    }

    fun decrementQuantity(product: CartModel) {
        if (product.quantity!! == 1) {
            deleteProduct(product)
            return
        }
        val id = product.id!!
        val quantity = product.quantity!! - 1
        val totalPrice = product.totalPrice!!.toInt() - product.price!!.toInt()
        updateProductQuantity(id, quantity, totalPrice)
    }
}