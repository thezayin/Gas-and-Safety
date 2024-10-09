package com.thezayin.presentation.events

import com.thezayin.databases.model.ProfileModel

/**
 * Sealed interface representing all possible UI events related to profiles.
 */
sealed interface AddressUiEvents {
    // Error and Loading States
    data class ShowError(val isError: Boolean) : AddressUiEvents
    data class ShowLoading(val isLoading: Boolean) : AddressUiEvents
    data class ErrorMessage(val errorMessage: String) : AddressUiEvents

    // Data Retrieval Events
    data class CityList(val cityList: List<String>) : AddressUiEvents
    data class AreaList(val areaList: List<String>) : AddressUiEvents
    data class GetAddresses(val getAddresses: List<ProfileModel>?) : AddressUiEvents
    data class GetSuccess(val isGetSuccess: Boolean) : AddressUiEvents

    // Add Profile Events
    data class AddAddress(val addAddress: ProfileModel?) : AddressUiEvents
    data class AddSuccess(val isAddSuccess: Boolean) : AddressUiEvents

    // Update Profile Events
    data class UpdateAddress(val updateAddress: ProfileModel?) : AddressUiEvents
    data class UpdateSuccess(val isUpdateSuccess: Boolean) : AddressUiEvents

    // Delete Profile Events
    data class DeleteAddress(val deleteAddress: ProfileModel?) : AddressUiEvents
    data class DeleteSuccess(val isDeleteSuccess: Boolean) : AddressUiEvents

    // Delete All Profiles Events
    data class DeleteAllSuccess(val isDeleteAllSuccess: Boolean) : AddressUiEvents
}