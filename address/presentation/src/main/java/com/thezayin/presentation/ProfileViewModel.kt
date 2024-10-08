package com.thezayin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.usecase.AddProfile
import com.thezayin.domain.usecase.DeleteAllProfiles
import com.thezayin.domain.usecase.DeleteProfileById
import com.thezayin.domain.usecase.GetAllProfiles
import com.thezayin.domain.usecase.GetAreaList
import com.thezayin.domain.usecase.GetCityList
import com.thezayin.domain.usecase.GetProfileDataById
import com.thezayin.domain.usecase.UpdateProfileById
import com.thezayin.framework.utils.Response
import com.thezayin.presentation.events.AddressUiEvents
import com.thezayin.presentation.state.AddressUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val addProfile: AddProfile,
    private val getAllProfiles: GetAllProfiles,
    private val updateProfileById: UpdateProfileById,
    private val deleteProfileById: DeleteProfileById,
    private val getProfileDataById: GetProfileDataById,
    private val deleteAllProfiles: DeleteAllProfiles,
    private val getCityList: GetCityList,
    private val getAreaList: GetAreaList
) : ViewModel() {
    private val _addressUiState = MutableStateFlow(AddressUiState())
    val addressUiState = _addressUiState.asStateFlow()


    init {
        fetchAllProfiles()
        fetchCityList()
    }

    private fun addressEvents(events: AddressUiEvents) {
        when (events) {
            is AddressUiEvents.ShowError -> _addressUiState.update { it.copy(isError = events.isError) }
            is AddressUiEvents.AreaList -> _addressUiState.update { it.copy(areaList = events.areaList) }
            is AddressUiEvents.CityList -> _addressUiState.update { it.copy(cityList = events.cityList) }
            is AddressUiEvents.ShowLoading -> _addressUiState.update { it.copy(isLoading = events.isLoading) }
            is AddressUiEvents.AddAddress -> _addressUiState.update { it.copy(addAddress = events.addAddress) }
            is AddressUiEvents.AddSuccess -> _addressUiState.update { it.copy(isAddSuccess = events.isAddSuccess) }
            is AddressUiEvents.GetSuccess -> _addressUiState.update { it.copy(isGetSuccess = events.isGetSuccess) }
            is AddressUiEvents.ErrorMessage -> _addressUiState.update { it.copy(errorMessage = events.errorMessage) }
            is AddressUiEvents.GetAddresses -> _addressUiState.update { it.copy(getAddresses = events.getAddresses) }
            is AddressUiEvents.DeleteAddress -> _addressUiState.update { it.copy(deleteAddress = events.deleteAddress) }
            is AddressUiEvents.UpdateAddress -> _addressUiState.update { it.copy(updateAddress = events.updateAddress) }
            is AddressUiEvents.UpdateSuccess -> _addressUiState.update { it.copy(isUpdateSuccess = events.isUpdateSuccess) }
            is AddressUiEvents.DeleteSuccess -> _addressUiState.update { it.copy(isDeleteSuccess = events.isDeleteSuccess) }
        }
    }

    private fun errorMessage(message: String) = addressEvents(AddressUiEvents.ErrorMessage(message))
    private fun showLoading(loading: Boolean) = addressEvents(AddressUiEvents.ShowLoading(loading))
    private fun areaList(list: List<String>) = addressEvents(AddressUiEvents.AreaList(list))
    private fun cityList(list: List<String>) = addressEvents(AddressUiEvents.CityList(list))
    fun showError(error: Boolean) = addressEvents(AddressUiEvents.ShowError(error))

    private fun deleteAddress(address: ProfileModel?) =
        addressEvents(AddressUiEvents.DeleteAddress(address))

    private fun updateAddress(address: ProfileModel?) =
        addressEvents(AddressUiEvents.UpdateAddress(address))

    private fun updateSuccess(isSuccess: Boolean) =
        addressEvents(AddressUiEvents.UpdateSuccess(isSuccess))

    private fun deleteSuccess(isSuccess: Boolean) =
        addressEvents(AddressUiEvents.DeleteSuccess(isSuccess))

    private fun getAddresses(list: List<ProfileModel>?) =
        addressEvents(AddressUiEvents.GetAddresses(list))

    private fun addAddress(address: ProfileModel?) =
        addressEvents(AddressUiEvents.AddAddress(address))

    private fun addSuccess(isSuccess: Boolean) =
        addressEvents(AddressUiEvents.AddSuccess(isSuccess))

    private fun getSuccess(isSuccess: Boolean) =
        addressEvents(AddressUiEvents.GetSuccess(isSuccess))

    private fun fetchCityList() = viewModelScope.launch {
        getCityList().collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    cityList(response.data)
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    fun fetchAreaList(city: String) = viewModelScope.launch {
        getAreaList(city).collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    areaList(response.data)
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    private fun fetchAllProfiles() = viewModelScope.launch {
        getAllProfiles().collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    getAddresses(response.data)
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    fun addNewProfile(
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String?
    ) = viewModelScope.launch {
        addProfile(name, phoneNumber, address, area, city, email).collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    addSuccess(response.data)
                    fetchAllProfiles()
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    fun updateProfile(
        id: Int, name: String, phoneNumber: String, address: String, email: String
    ) = viewModelScope.launch {
        updateProfileById(id, name, phoneNumber, address, email).collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    updateSuccess(response.data)
                    fetchAllProfiles()
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    fun fetchProfileById(id: Int) = viewModelScope.launch {
        getProfileDataById(id).collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    updateAddress(response.data)
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    fun deleteProfile(id: Int) = viewModelScope.launch {
        deleteProfileById(id).collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    deleteSuccess(response.data)
                    fetchAllProfiles()
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }

    fun deleteAll() = viewModelScope.launch {
        deleteAllProfiles().collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    deleteSuccess(response.data)
                }

                is Response.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> showLoading(true)
            }
        }
    }
}