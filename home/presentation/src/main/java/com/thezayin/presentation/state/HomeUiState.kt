package com.thezayin.presentation.state

import com.thezayin.databases.model.CartModel
import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.model.HomeProdModel

/**
 * Data class representing the UI state for the Home screen, including cart and products.
 *
 * @property isLoading Indicates whether data is currently loading.
 * @property getProducts Holds the list of products fetched from the database or API.
 * @property isError Indicates if an error occurred.
 * @property errorMessage Stores the error message, if any.
 * @property isAdded Indicates if an item was successfully added to the cart.
 * @property getCart Stores the current items in the cart.
 * @property getAddresses Stores the list of user addresses (optional).
 * @property productDetail Stores the details of the selected product.
 */
data class HomeUiState(
    val isLoading: Boolean = false,
    val getProducts: List<HomeProdModel>? = null,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isAdded: Boolean = false,
    val getCart: List<CartModel>? = null, // Cart data for products in the cart
    val getAddresses: List<ProfileModel>? = null,
    val productDetail: HomeProdModel? = null,
)