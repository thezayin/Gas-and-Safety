package com.thezayin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.databases.model.CartModel
import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.model.HomeProdModel
import com.thezayin.domain.usecase.AddToCart
import com.thezayin.domain.usecase.GetAllProfiles
import com.thezayin.domain.usecase.GetCartProducts
import com.thezayin.domain.usecase.GetProWithImg
import com.thezayin.domain.usecase.GetProducts
import com.thezayin.domain.usecase.UpdateQuantity
import com.thezayin.framework.utils.Response
import com.thezayin.presentation.events.HomeUiEvents
import com.thezayin.presentation.state.HomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val addToCart: AddToCart,
    private val getProduct: GetProducts,
    private val getProWithImg: GetProWithImg,
    private val getCartProducts: GetCartProducts,
    private val getAllProfiles: GetAllProfiles,
    private val updateQuantity: UpdateQuantity
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        getProductsFromFirebase()
        getAllProducts()
        fetchAllProfiles()
    }

    private fun homeUiEvent(event: HomeUiEvents) {
        when (event) {
            is HomeUiEvents.GetProducts -> _homeUiState.update { it.copy(getProducts = event.list) }
            is HomeUiEvents.ErrorMessage -> _homeUiState.update { it.copy(errorMessage = event.message) }
            is HomeUiEvents.ShowError -> _homeUiState.update { it.copy(isError = event.isError) }
            is HomeUiEvents.ShowLoading -> _homeUiState.update { it.copy(isLoading = event.isLoading) }
            is HomeUiEvents.AddToCart -> _homeUiState.update { it.copy(isAdded = event.isAdded) }
            is HomeUiEvents.GetCart -> _homeUiState.update { it.copy(getCart = event.cartList) }
            is HomeUiEvents.GetAddresses -> _homeUiState.update { it.copy(getAddresses = event.getAddresses) }
            is HomeUiEvents.ProductDetail -> _homeUiState.update { it.copy(productDetail = event.productDetail) }
        }
    }

    fun getProductDetail(product: HomeProdModel) = homeUiEvent(HomeUiEvents.ProductDetail(product))
    fun addedToCart(isAdd: Boolean) = homeUiEvent(HomeUiEvents.AddToCart(isAdd))
    private fun getCart(list: List<CartModel>) = homeUiEvent(HomeUiEvents.GetCart(list))
    private fun getProducts(list: List<HomeProdModel>) = homeUiEvent(HomeUiEvents.GetProducts(list))
    private fun errorMessage(message: String) = homeUiEvent(HomeUiEvents.ErrorMessage(message))
    private fun showLoading(isLoading: Boolean) = homeUiEvent(HomeUiEvents.ShowLoading(isLoading))
    fun showError(isError: Boolean) = homeUiEvent(HomeUiEvents.ShowError(isError))
    private fun getAddresses(list: List<ProfileModel>?) =
        homeUiEvent(HomeUiEvents.GetAddresses(list))

    fun getProduct(productId: String) = viewModelScope.launch {
        showLoading(true)
        delay(1000L)
        _homeUiState.collect {
            it.getProducts?.forEach { product ->
                if (product.id == productId) {
                    getProductDetail(product)
                    showLoading(false)
                } else {
                    showLoading(false)
                }
            }
            showLoading(false)
        }
    }

    private fun fetchProducts(list: List<HomeProdModel>) = viewModelScope.launch {
        getProWithImg(list).collect { response ->
            when (response) {
                is Response.Success -> {
                    getProducts(response.data)
                    showLoading(false)
                }

                is Response.Error -> {
                    showError(true)
                    errorMessage(response.e)
                    showError(false)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    private fun getAllProducts() = viewModelScope.launch {
        getCartProducts().collect { response ->
            when (response) {
                is Response.Success -> {
                    getCart(response.data)
                    showLoading(false)
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

    fun addedToCart(
        id: String, name: String, price: String, description: String, imageUri: String
    ) = viewModelScope.launch {
        val existingCartItem = _homeUiState.value.getCart?.find { it.id == id }
        if (existingCartItem != null) {
            val newQuantity = existingCartItem.quantity!! + 1
            val newTotalPrice = existingCartItem.totalPrice!!.toInt() + price.toInt()
            updateProductQuantity(id, newQuantity, newTotalPrice)
        } else {
            addToCart(id, name, price, description, imageUri).collect { response ->
                when (response) {
                    is Response.Success -> {
                        showLoading(false)
                        addedToCart(true)
                        getAllProducts()
                    }

                    is Response.Error -> {
                        showError(true)
                        errorMessage(response.e)
                    }

                    is Response.Loading -> showLoading(true)
                }
            }
        }
    }

    private fun getProductsFromFirebase() = viewModelScope.launch {
        getProduct().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchProducts(response.data)
                    showLoading(false)
                }

                is Response.Error -> {
                    errorMessage(response.e)
                    showLoading(false)
                    showError(true)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    private fun fetchAllProfiles() = viewModelScope.launch {
        getAllProfiles().collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    getAddresses(response.data)
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
                    addedToCart(true)
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
}