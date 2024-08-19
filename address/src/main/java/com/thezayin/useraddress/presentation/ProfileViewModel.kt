package com.thezayin.useraddress.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.GetSuccessState
import com.thezayin.entities.ProfileModel
import com.thezayin.framework.utils.Response
import com.thezayin.useraddress.domain.usecase.AddProfile
import com.thezayin.useraddress.domain.usecase.DeleteAllProfiles
import com.thezayin.useraddress.domain.usecase.DeleteProfileById
import com.thezayin.useraddress.domain.usecase.GetAllProfiles
import com.thezayin.useraddress.domain.usecase.GetAreaList
import com.thezayin.useraddress.domain.usecase.GetCityList
import com.thezayin.useraddress.domain.usecase.GetProfileDataById
import com.thezayin.useraddress.domain.usecase.UpdateProfileById
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
    private val _getProfileList = MutableStateFlow(GetProfileState())
    val getProfileList = _getProfileList.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _isAddSuccess = MutableStateFlow(GetSuccessState())
    val isAddSuccess = _isAddSuccess.asStateFlow()

    private val _isDeleteSuccess = MutableStateFlow(GetSuccessState())
    val isDeleteSuccess = _isDeleteSuccess.asStateFlow()

    private val _isDeleteAllSuccess = MutableStateFlow(GetSuccessState())
    val isDeleteAllSuccess = _isDeleteAllSuccess.asStateFlow()

    private val _isUpdateSuccess = MutableStateFlow(GetSuccessState())
    val isUpdateSuccess = _isUpdateSuccess.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _getProfileById = MutableStateFlow(GetProfileByIdState())
    val getProfileById = _getProfileById.asStateFlow()

    private val _getAreaState = MutableStateFlow(GetAreaState())
    val getAreaState = _getAreaState.asStateFlow()

    private val _getCityState = MutableStateFlow(GetCityState())
    val getCityState = _getCityState.asStateFlow()


    private val _selectedItemIndex = MutableStateFlow(-1)
    val selectedItemIndex = _selectedItemIndex.asStateFlow()


    init {
        fetchAllProfiles()
        fetchCityList()
    }

    fun onItemSelected(index: Int) {
        _selectedItemIndex.value = index
    }

    private fun fetchCityList() = viewModelScope.launch {
        getCityList().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _getCityState.update { it.copy(data = response.data) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun fetchAreaList(city: String) = viewModelScope.launch {
        getAreaList(city).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _getAreaState.update { it.copy(data = response.data) }
                }

                is Response.Error -> {

                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun fetchAllProfiles() = viewModelScope.launch {
        getAllProfiles().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _getProfileList.update { it.copy(data = response.data) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
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
                    _isLoading.update { it.copy(isLoading = false) }
                    _isAddSuccess.update { it.copy(isSuccess = response.data) }
                    fetchAllProfiles()
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun updateProfile(
        id: Int,
        name: String,
        phoneNumber: String,
        address: String,
        email: String
    ) = viewModelScope.launch {
        updateProfileById(id, name, phoneNumber, address, email).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isUpdateSuccess.update { it.copy(isSuccess = response.data) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun fetchProfileById(id: Int) = viewModelScope.launch {
        getProfileDataById(id).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _getProfileById.update { it.copy(data = response.data) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun deleteProfile(id: Int) = viewModelScope.launch {
        deleteProfileById(id).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isDeleteSuccess.update { it.copy(isSuccess = response.data) }
                    fetchAllProfiles()
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun deleteAll() = viewModelScope.launch {
        deleteAllProfiles().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isDeleteAllSuccess.update { it.copy(isSuccess = response.data) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    data class GetAreaState(val data: List<String> = emptyList())
    data class GetCityState(val data: List<String> = emptyList())
    data class GetProfileState(val data: List<ProfileModel> = emptyList())
    data class GetProfileByIdState(val data: ProfileModel = ProfileModel())
}