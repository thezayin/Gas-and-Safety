package com.thezayin.presentation.state

import com.thezayin.databases.model.CartModel
import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.model.HomeProdModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val getProducts: List<HomeProdModel>? = null,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isAdded: Boolean = false,
    val getCart: List<CartModel>? = null,
    val getAddresses: List<ProfileModel>? = null,
    val productDetail: HomeProdModel? = null,
)