package com.thezayin.lpg.presentation.users.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.framework.utils.Response
import com.thezayin.usecase.AddToCart
import com.thezayin.usecase.DeleteAllCart
import com.thezayin.usecase.DeleteFromCart
import com.thezayin.usecase.GetCartProducts
import com.thezayin.usecase.UpdateQuantity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val addToCart: com.thezayin.usecase.AddToCart,
    private val deleteFromCart: com.thezayin.usecase.DeleteFromCart,
    private val deleteAllCart: com.thezayin.usecase.DeleteAllCart,
    private val getCartProducts: com.thezayin.usecase.GetCartProducts,
    private val updateQuantity: com.thezayin.usecase.UpdateQuantity
) : ViewModel() {
    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _getProducts = MutableStateFlow(GetProductState())
    val getProducts = _getProducts.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isAddedToCart = MutableStateFlow(GetAddToCartState())
    val isAddedToCart = _isAddedToCart.asStateFlow()

    private val _isDeleteQuery = MutableStateFlow(GetDeleteQueryState())
    val isDeleteQuery = _isDeleteQuery.asStateFlow()

    init {
        getAllProducts()
    }

    fun deleteQuery(boolean: Boolean) {
        _isDeleteQuery.update { it.copy(isSuccess = boolean) }
    }

    fun updateCartValue(boolean: Boolean) {
        _isAddedToCart.update { it.copy(isAdded = boolean) }
    }

    private fun getAllProducts() = viewModelScope.launch {
        getCartProducts().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _getProducts.update { it.copy(list = response.data) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update {
                        it.copy(isError = true, errorMessage = response.e)
                    }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun updateProductQuantity(id: String, qty: Int, price: Int) = viewModelScope.launch {
        updateQuantity(id, qty, price).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    getAllProducts()
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update {
                        it.copy(isError = true, errorMessage = response.e)
                    }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun addedToCart(model: com.thezayin.entities.HomeProductsModel) = viewModelScope.launch {
        addToCart(model).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    updateCartValue(true)
                    getAllProducts()
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update {
                        it.copy(isError = true, errorMessage = response.e)
                    }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun emptyCart() = viewModelScope.launch {
        deleteAllCart().collect { response ->
            when (response) {
                is Response.Success -> {
                    deleteQuery(response.data)
                    _isLoading.update { it.copy(isLoading = false) }
                    getAllProducts()
                }

                is Response.Error -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    _isQueryError.update {
                        it.copy(
                            isError = true,
                            errorMessage = it.errorMessage
                        )
                    }
                }

                is Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }

    private fun deleteProduct(product: com.thezayin.entities.CartModel) = viewModelScope.launch {
        deleteFromCart(product.id!!).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    getAllProducts()
                }

                is Response.Error -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    _isQueryError.update {
                        it.copy(
                            isError = true,
                            errorMessage = it.errorMessage
                        )
                    }
                }

                is Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun incrementQuantity(product: com.thezayin.entities.CartModel) = viewModelScope.launch {
        val quantity = product.quantity!! + 1
        val totalPrice = product.totalPrice!!.toInt() + product.price!!.toInt()
        val id = product.id!!
        updateProductQuantity(id, quantity, totalPrice)
    }

    fun decrementQuantity(product: com.thezayin.entities.CartModel) {
        if (product.quantity!! == 1) {
            deleteProduct(product)
            return
        }
        val id = product.id!!
        val quantity = product.quantity!! - 1
        val totalPrice = product.totalPrice!!.toInt() - product.price!!.toInt()
        updateProductQuantity(id, quantity, totalPrice)
    }

    data class GetLoadingState(val isLoading: Boolean = false)
    data class GetProductState(val list: List<com.thezayin.entities.CartModel> = emptyList())
    data class GetErrorState(val isError: Boolean = false, val errorMessage: String = "")
    data class GetAddToCartState(val isAdded: Boolean = false)
    data class GetDeleteQueryState(val isSuccess: Boolean = false)
}