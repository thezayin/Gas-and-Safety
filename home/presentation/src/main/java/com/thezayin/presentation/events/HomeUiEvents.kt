package com.thezayin.presentation.events

import com.thezayin.databases.model.CartModel
import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.model.HomeProdModel

/**
 * Sealed class representing all possible UI events related to the Home screen, including cart operations.
 */
sealed interface HomeUiEvents {
    data class ErrorMessage(val message: String) : HomeUiEvents
    data class ShowLoading(val isLoading: Boolean) : HomeUiEvents
    data class ShowError(val isError: Boolean) : HomeUiEvents
    data class GetProducts(val list: List<HomeProdModel>) : HomeUiEvents

    // Cart-related events
    data class AddToCart(val isAdded: Boolean) : HomeUiEvents
    data class GetCart(val cartList: List<CartModel>) : HomeUiEvents

    // Other events
    data class GetAddresses(val getAddresses: List<ProfileModel>?) : HomeUiEvents
    data class ProductDetail(val productDetail: HomeProdModel) : HomeUiEvents
}