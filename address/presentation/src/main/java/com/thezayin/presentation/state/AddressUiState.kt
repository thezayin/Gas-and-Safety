package com.thezayin.presentation.state

import com.thezayin.databases.model.ProfileModel

/**
 * Data class representing the UI state for profile-related operations.
 *
 * @property isError Indicates if an error has occurred.
 * @property isLoading Indicates if a loading operation is in progress.
 * @property isAddSuccess Indicates if adding a profile was successful.
 * @property isGetSuccess Indicates if fetching a profile was successful.
 * @property isDeleteSuccess Indicates if deleting a profile was successful.
 * @property isDeleteAllSuccess Indicates if deleting all profiles was successful.
 * @property isUpdateSuccess Indicates if updating a profile was successful.
 * @property addAddress The profile data to be added.
 * @property getAddresses The list of all profiles.
 * @property errorMessage The error message to display.
 * @property deleteAddress The profile data to be deleted.
 * @property updateAddress The profile data to be updated.
 * @property cityList The list of available cities.
 * @property areaList The list of areas within a selected city.
 */
data class AddressUiState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val isAddSuccess: Boolean = false,
    val isGetSuccess: Boolean = false,
    val isDeleteSuccess: Boolean = false,
    val isDeleteAllSuccess: Boolean = false,
    val isUpdateSuccess: Boolean = false,
    val addAddress: ProfileModel? = null,
    val getAddresses: List<ProfileModel>? = null,
    val errorMessage: String = "Unexpected Error",
    val deleteAddress: ProfileModel? = null,
    val updateAddress: ProfileModel? = null,
    val cityList: List<String> = listOf(
        "Select",
        "Islamabad",
        "Rawalpindi"
    ),
    val areaList: List<String> = emptyList(),
)