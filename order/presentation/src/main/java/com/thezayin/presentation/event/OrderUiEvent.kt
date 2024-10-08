package com.thezayin.presentation.event

import com.thezayin.databases.model.CartModel
import com.thezayin.databases.model.ProfileModel

sealed interface OrderUiEvent {
    data class ShowLoading(val isLoading: Boolean) : OrderUiEvent
    data class ShowError(val isError: Boolean) : OrderUiEvent
    data class ShowErrorMessage(val errorMessage: String) : OrderUiEvent
    data class OrderList(val orderList: List<CartModel>) : OrderUiEvent
    data class OrderSuccess(val orderSuccess: Boolean) : OrderUiEvent
    data class OrderDeleteSuccess(val isDeleteSuccess: Boolean) : OrderUiEvent
    data class CityList(val cityList: List<String>) : OrderUiEvent
    data class AreaList(val areaList: List<String>) : OrderUiEvent
    data class GetAddresses(val getAddresses: List<ProfileModel>?) : OrderUiEvent
    data class GetAddressById(val address: ProfileModel) : OrderUiEvent

}