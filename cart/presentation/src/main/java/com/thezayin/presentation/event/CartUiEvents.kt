package com.thezayin.presentation.event

import com.thezayin.databases.model.CartModel

sealed interface CartUiEvents {
    data class ShowLoading(val isLoading: Boolean) : CartUiEvents
    data class ShowError(val isError: Boolean) : CartUiEvents
    data class ErrorMessage(val message: String) : CartUiEvents
    data class AddedToCart(val isAdded: Boolean) : CartUiEvents
    data class RemovedFromCart(val isRemoved: Boolean) : CartUiEvents
    data class GetProducts(val list: List<CartModel>) : CartUiEvents
}