package com.thezayin.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.databases.model.CartModel
import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.model.HomeProdModel
import com.thezayin.domain.usecase.AddProductToCart
import com.thezayin.domain.usecase.AddToCartParams
import com.thezayin.domain.usecase.FetchCartProducts
import com.thezayin.domain.usecase.FetchProductsUseCase
import com.thezayin.domain.usecase.FetchProductsWithImagesUseCase
import com.thezayin.domain.usecase.GetAllProfilesUseCase
import com.thezayin.domain.usecase.UpdateCartProductQuantityUseCase
import com.thezayin.domain.usecase.UpdateCartQuantityParams
import com.thezayin.framework.utils.Resource
import com.thezayin.presentation.events.HomeUiEvents
import com.thezayin.presentation.state.HomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Home screen, responsible for managing UI-related data and handling user interactions.
 *
 * This ViewModel interacts with various use cases to fetch products, manage the shopping cart,
 * and retrieve user profiles. It also manages the UI state for the Home screen.
 */
class HomeViewModel(
    private val addProductToCart: AddProductToCart,
    private val fetchProducts: FetchProductsUseCase,
    private val fetchProductsWithImages: FetchProductsWithImagesUseCase,
    private val getCartProducts: FetchCartProducts,
    private val getAllProfiles: GetAllProfilesUseCase,
    private val updateCartProductQuantity: UpdateCartProductQuantityUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        // Initial fetching of products, cart items, and user profiles
        fetchProductsFromFirebase()
        fetchAllCartItems()
        fetchAllUserProfiles()
    }

    // Convenience methods for event handling
    private fun handleHomeUiEvent(event: HomeUiEvents) {
        when (event) {
            is HomeUiEvents.GetProducts ->
                _homeUiState.update { it.copy(getProducts = event.list) } // Update products in UI state

            is HomeUiEvents.ErrorMessage ->
                _homeUiState.update { it.copy(errorMessage = event.message) } // Update error message

            is HomeUiEvents.ShowError ->
                _homeUiState.update { it.copy(isError = event.isError) } // Show or hide error

            is HomeUiEvents.ShowLoading ->
                _homeUiState.update { it.copy(isLoading = event.isLoading) } // Show loading indicator

            is HomeUiEvents.AddToCart ->
                _homeUiState.update { it.copy(isAdded = event.isAdded) } // Update cart status

            is HomeUiEvents.GetCart ->
                _homeUiState.update { it.copy(getCart = event.cartList) } // Update cart items

            is HomeUiEvents.GetAddresses ->
                _homeUiState.update { it.copy(getAddresses = event.getAddresses) } // Update addresses

            is HomeUiEvents.ProductDetail ->
                _homeUiState.update { it.copy(productDetail = event.productDetail) } // Update product detail
        }
    }

    // Emit product detail event
    private fun emitProductDetail(product: HomeProdModel) =
        handleHomeUiEvent(HomeUiEvents.ProductDetail(product))

    // Emit event indicating item was added to the cart
    fun emitAddedToCart(isAdd: Boolean) = handleHomeUiEvent(HomeUiEvents.AddToCart(isAdd))

    // Emit cart items to the UI
    private fun emitCart(list: List<CartModel>) = handleHomeUiEvent(HomeUiEvents.GetCart(list))

    // Emit the list of products to the UI
    private fun emitProducts(list: List<HomeProdModel>) =
        handleHomeUiEvent(HomeUiEvents.GetProducts(list))

    // Emit error messages to the UI
    private fun emitErrorMessage(message: String) =
        handleHomeUiEvent(HomeUiEvents.ErrorMessage(message))

    // Emit loading state to the UI
    private fun emitShowLoading(isLoading: Boolean) =
        handleHomeUiEvent(HomeUiEvents.ShowLoading(isLoading))

    // Emit error state to the UI
    fun emitShowError(isError: Boolean) = handleHomeUiEvent(HomeUiEvents.ShowError(isError))

    // Emit addresses to the UI
    private fun emitAddresses(list: List<ProfileModel>?) =
        handleHomeUiEvent(HomeUiEvents.GetAddresses(list))

    // Fetch product detail when user selects a product
    fun getProductDetail(product: HomeProdModel) = emitProductDetail(product)

    // Fetch a product by ID
    fun fetchProduct(productId: String) = viewModelScope.launch {
        emitShowLoading(true)
        delay(1000L) // Simulate loading delay
        _homeUiState.collect { state ->
            state.getProducts?.forEach { product ->
                if (product.id == productId) {
                    getProductDetail(product)
                    emitShowLoading(false)
                } else {
                    emitShowLoading(false)
                }
            }
            emitShowLoading(false)
        }
    }

    // Fetch products from Firebase
    private fun fetchProductsFromFirebase() = viewModelScope.launch {
        fetchProducts().collect { response ->
            when (response) {
                is Resource.Success -> {
                    emitShowLoading(false)
                    fetchProductsImages(response.data)
                }

                is Resource.Error -> {
                    emitShowLoading(false)
                    emitErrorMessage(response.e)
                    emitShowError(true)
                }

                is Resource.Loading -> {
                    emitShowLoading(true)
                }
            }
        }
    }

    // Fetch images for the products
    private fun fetchProductsImages(products: List<HomeProdModel>) = viewModelScope.launch {
        fetchProductsWithImages(products).collect { response ->
            when (response) {
                is Resource.Success -> {
                    emitShowLoading(false)
                    emitProducts(response.data) // Update the UI with products
                }

                is Resource.Error -> {
                    emitShowLoading(false)
                    emitErrorMessage(response.e)
                    emitShowError(true)
                }

                is Resource.Loading -> {
                    emitShowLoading(true)
                }
            }
        }
    }

    // Fetch all cart items
    private fun fetchAllCartItems() = viewModelScope.launch {
        getCartProducts().collect { response ->
            when (response) {
                is Resource.Success -> {
                    emitShowLoading(false)
                    emitCart(response.data) // Update cart items
                }

                is Resource.Error -> {
                    emitShowLoading(false)
                    emitErrorMessage(response.e)
                    emitShowError(true)
                }

                is Resource.Loading -> {
                    emitShowLoading(true)
                }
            }
        }
    }

    // Fetch all user profiles
    private fun fetchAllUserProfiles() = viewModelScope.launch {
        getAllProfiles().collect { response ->
            when (response) {
                is Resource.Success -> {
                    emitShowLoading(false)
                    emitAddresses(response.data) // Update addresses
                }

                is Resource.Error -> {
                    emitShowLoading(false)
                    emitErrorMessage(response.e)
                    emitShowError(true)
                }

                is Resource.Loading -> {
                    emitShowLoading(true)
                }
            }
        }
    }

    // Add a product to the cart
    fun addToCart(id: String, name: String, price: String, description: String, imageUri: String) =
        viewModelScope.launch {
            val existingCartItem = _homeUiState.value.getCart?.find { it.externalId == id }
            if (existingCartItem != null) {
                Log.d("jeje", "Cart Item already exists in cart")
                val newQuantity = existingCartItem.quantity + 1
                val newTotalPrice = existingCartItem.totalPrice + price.toInt()
                updateProductQuantity(id, newQuantity, newTotalPrice.toInt())
                Log.d("jeje", "New Quantity: $newQuantity")
                emitShowLoading(false)
            } else {
                val params = AddToCartParams(id, name, price, description, imageUri)
                addProductToCart(params).collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            Log.d("jeje", "Product added to cart")
                            emitShowLoading(false)
                            emitAddedToCart(true)
                            fetchAllCartItems() // Refresh cart items
                        }

                        is Resource.Error -> {
                            emitShowLoading(false)
                            emitShowError(true)
                            emitErrorMessage(response.e)
                        }

                        is Resource.Loading -> {
                            Log.d("jeje", "Adding product to cart")
                            emitShowLoading(true)
                        }
                    }
                }
            }
        }

    // Update the quantity of a product in the cart
    private fun updateProductQuantity(id: String, qty: Int, price: Int) = viewModelScope.launch {
        val params = UpdateCartQuantityParams(id, qty, price)
        updateCartProductQuantity(params).collect { response ->
            when (response) {
                is Resource.Success -> {
                    emitShowLoading(false)
                    fetchAllCartItems() // Refresh cart after update
                }

                is Resource.Error -> {
                    emitShowLoading(false)
                    emitShowError(true)
                    emitErrorMessage(response.e)
                }

                is Resource.Loading -> {
                    emitShowLoading(true)
                }
            }
        }
    }
}
