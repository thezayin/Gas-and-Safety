package com.thezayin.presentation.state

import com.thezayin.databases.model.CartModel

data class CartUiState(
    val isLoading: Boolean = false,
    val getProducts: List<CartModel>? = null,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val productAdded: Boolean = false,
    val productRemoved: Boolean = false
)