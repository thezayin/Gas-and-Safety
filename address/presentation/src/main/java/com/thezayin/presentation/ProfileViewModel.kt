package com.thezayin.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.usecase.AddProfileParams
import com.thezayin.domain.usecase.AddProfileUseCase
import com.thezayin.domain.usecase.DeleteAllProfilesUseCase
import com.thezayin.domain.usecase.DeleteProfileByIdParams
import com.thezayin.domain.usecase.DeleteProfileByIdUseCase
import com.thezayin.domain.usecase.GetAllProfilesUseCase
import com.thezayin.domain.usecase.GetAreaListParams
import com.thezayin.domain.usecase.GetAreaListUseCase
import com.thezayin.domain.usecase.GetCityListUseCase
import com.thezayin.domain.usecase.GetProfileByIdParams
import com.thezayin.domain.usecase.GetProfileByIdUseCase
import com.thezayin.domain.usecase.UpdateProfileParams
import com.thezayin.domain.usecase.UpdateProfileUseCase
import com.thezayin.framework.utils.Resource
import com.thezayin.presentation.events.AddressUiEvents
import com.thezayin.presentation.state.AddressUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for managing profile-related operations.
 *
 * @property addProfileUseCase The use case for adding a new profile.
 * @property getAllProfilesUseCase The use case for retrieving all profiles.
 * @property updateProfileUseCase The use case for updating a profile.
 * @property deleteProfileByIdUseCase The use case for deleting a profile by ID.
 * @property getProfileByIdUseCase The use case for fetching a profile by ID.
 * @property deleteAllProfilesUseCase The use case for deleting all profiles.
 * @property getCityListUseCase The use case for retrieving the list of cities.
 * @property getAreaListUseCase The use case for retrieving the list of areas within a city.
 */
class ProfileViewModel(
    private val addProfileUseCase: AddProfileUseCase,
    private val getAllProfilesUseCase: GetAllProfilesUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val deleteProfileByIdUseCase: DeleteProfileByIdUseCase,
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val deleteAllProfilesUseCase: DeleteAllProfilesUseCase,
    private val getCityListUseCase: GetCityListUseCase,
    private val getAreaListUseCase: GetAreaListUseCase
) : ViewModel() {

    private val _addressUiState = MutableStateFlow(AddressUiState())
    val addressUiState = _addressUiState.asStateFlow()

    init {
        fetchAllProfiles()
        fetchCityList()
    }

    /**
     * Handles UI events by updating the UI state accordingly.
     *
     * @param events The UI event to handle.
     */
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
            is AddressUiEvents.DeleteAllSuccess -> _addressUiState.update {
                it.copy(
                    isDeleteAllSuccess = events.isDeleteAllSuccess
                )
            }
        }
    }

    // Convenience methods to emit specific UI events
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

    private fun deleteAllSuccess(isSuccess: Boolean) =
        addressEvents(AddressUiEvents.DeleteAllSuccess(isSuccess))

    private fun getAddresses(list: List<ProfileModel>?) =
        addressEvents(AddressUiEvents.GetAddresses(list))

    private fun addAddress(address: ProfileModel?) =
        addressEvents(AddressUiEvents.AddAddress(address))

    private fun addSuccess(isSuccess: Boolean) =
        addressEvents(AddressUiEvents.AddSuccess(isSuccess))

    private fun getSuccess(isSuccess: Boolean) =
        addressEvents(AddressUiEvents.GetSuccess(isSuccess))

    /**
     * Fetches the list of cities and updates the UI state.
     */
    private fun fetchCityList() = viewModelScope.launch {
        getCityListUseCase().collect { response ->
            when (response) {
                is Resource.Success -> {
                    cityList(response.data)
                    Log.d("ProfileViewModel", "City list fetched: ${response.data}")
                    showLoading(false)
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }

    /**
     * Fetches the list of areas for a given city and updates the UI state.
     *
     * @param city The name of the city for which to fetch areas.
     */
    fun fetchAreaList(city: String) = viewModelScope.launch {
        getAreaListUseCase(GetAreaListParams(city)).collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    areaList(response.data)
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }

    /**
     * Fetches all user profiles and updates the UI state.
     */
    private fun fetchAllProfiles() = viewModelScope.launch {
        getAllProfilesUseCase().collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    getAddresses(response.data)
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }

    /**
     * Adds a new user profile and updates the UI state.
     *
     * @param name The full name of the user.
     * @param phoneNumber The user's contact phone number.
     * @param address The primary address of the user.
     * @param area The area or neighborhood of the user's address.
     * @param city The city where the user resides.
     * @param email The user's email address. Optional.
     */
    fun addNewProfile(
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String?
    ) = viewModelScope.launch {
        addProfileUseCase(
            AddProfileParams(
                name = name,
                phoneNumber = phoneNumber,
                address = address,
                area = area,
                city = city,
                email = email
            )
        ).collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    addSuccess(response.data)
                    fetchAllProfiles()
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }

    /**
     * Updates an existing user profile and updates the UI state.
     *
     * @param id The unique identifier of the profile to update.
     * @param name The updated name of the user.
     * @param phoneNumber The updated phone number of the user.
     * @param address The updated address of the user.
     * @param area The updated geographical area of the user.
     * @param city The updated city of residence of the user.
     * @param email The updated email of the user. Optional.
     */
    fun updateProfile(
        id: Int,
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String?
    ) = viewModelScope.launch {
        updateProfileUseCase.execute(
            UpdateProfileParams(
                id = id,
                name = name,
                phoneNumber = phoneNumber,
                address = address,
                area = area,
                city = city,
                email = email
            )
        ).collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    updateSuccess(response.data)
                    fetchAllProfiles()
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }

    /**
     * Fetches a user profile by its ID and updates the UI state.
     *
     * @param id The unique identifier of the profile to fetch.
     */
    fun fetchProfileById(id: Int) = viewModelScope.launch {
        getProfileByIdUseCase(GetProfileByIdParams(id)).collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    updateAddress(response.data)
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }

    /**
     * Deletes a user profile by its ID and updates the UI state.
     *
     * @param id The unique identifier of the profile to delete.
     */
    fun deleteProfile(id: Int) = viewModelScope.launch {
        deleteProfileByIdUseCase.execute(DeleteProfileByIdParams(id)).collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    deleteSuccess(response.data)
                    fetchAllProfiles()
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }

    /**
     * Deletes all user profiles and updates the UI state.
     */
    fun deleteAll() = viewModelScope.launch {
        deleteAllProfilesUseCase.execute().collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    deleteAllSuccess(response.data)
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    errorMessage(response.e)
                }

                is Resource.Loading -> showLoading(true)
            }
        }
    }
}