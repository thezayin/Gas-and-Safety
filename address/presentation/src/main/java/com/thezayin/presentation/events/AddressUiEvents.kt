package com.thezayin.presentation.events

import com.thezayin.databases.model.ProfileModel

sealed interface AddressUiEvents {
    data class ShowError(val isError: Boolean) : AddressUiEvents
    data class ShowLoading(val isLoading: Boolean) : AddressUiEvents
    data class CityList(val cityList: List<String>) : AddressUiEvents
    data class AreaList(val areaList: List<String>) : AddressUiEvents
    data class AddSuccess(val isAddSuccess: Boolean) : AddressUiEvents
    data class GetSuccess(val isGetSuccess: Boolean) : AddressUiEvents
    data class ErrorMessage(val errorMessage: String) : AddressUiEvents
    data class AddAddress(val addAddress: ProfileModel?) : AddressUiEvents
    data class DeleteSuccess(val isDeleteSuccess: Boolean) : AddressUiEvents
    data class UpdateSuccess(val isUpdateSuccess: Boolean) : AddressUiEvents
    data class DeleteAddress(val deleteAddress: ProfileModel?) : AddressUiEvents
    data class UpdateAddress(val updateAddress: ProfileModel?) : AddressUiEvents
    data class GetAddresses(val getAddresses: List<ProfileModel>?) : AddressUiEvents
}