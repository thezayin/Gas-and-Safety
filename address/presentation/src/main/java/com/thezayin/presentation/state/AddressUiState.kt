package com.thezayin.presentation.state

import com.thezayin.databases.model.ProfileModel

data class AddressUiState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val isAddSuccess: Boolean = false,
    val isGetSuccess: Boolean = false,
    val isDeleteSuccess: Boolean = false,
    val isUpdateSuccess: Boolean = false,
    val addAddress: ProfileModel? = null,
    val getAddresses: List<ProfileModel>? = null,
    val errorMessage: String = "UnExpected Error",
    val deleteAddress: ProfileModel? = null,
    val updateAddress: ProfileModel? = null,
    val cityList: List<String> = emptyList(),
    val areaList: List<String> = emptyList(),
)