package com.thezayin.presentation.state

import com.thezayin.databases.model.CartModel
import com.thezayin.databases.model.ProfileModel

data class OrderUiState(
    val isLoading: Boolean = false,
    val isDeleteSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val orderList: List<CartModel> = emptyList(),
    val orderSuccess: Boolean = false,
    val cityList: List<String> = emptyList(),
    val areaList: List<String> = emptyList(),
    val getAddresses: List<ProfileModel>? = null,
    val address: ProfileModel = ProfileModel()
)