package com.thezayin.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.databases.model.CartModel
import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.usecase.AddProfile
import com.thezayin.domain.usecase.DeleteAllCart
import com.thezayin.domain.usecase.GetAllProfiles
import com.thezayin.domain.usecase.GetAreaList
import com.thezayin.domain.usecase.GetCartProducts
import com.thezayin.domain.usecase.GetCityList
import com.thezayin.domain.usecase.GetProfileDataById
import com.thezayin.domain.usecase.PlaceOrder
import com.thezayin.framework.utils.Response
import com.thezayin.presentation.event.OrderUiEvent
import com.thezayin.presentation.state.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

class OrderViewModel(
    private val addProfile: AddProfile,
    val placeOrder: PlaceOrder,
    private val getAllProfiles: GetAllProfiles,
    private val getCartProducts: GetCartProducts,
    private val deleteAllCart: DeleteAllCart,
    private val getProfileDataById: GetProfileDataById,
    private val getCityList: GetCityList,
    private val getAreaList: GetAreaList
) : ViewModel() {

    private val _orderUiState = MutableStateFlow(OrderUiState())
    val orderUiState = _orderUiState.asStateFlow()

    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("dd/M/yyyy")
    private val currentDate: String = sdf.format(Date())
    private val orderTime: String = SimpleDateFormat("HH:mm:ss").format(Date())
    val date = LocalDateTime.now()

    init {
        fetchAllProfiles()
        getAllProducts()
        fetchCityList()
    }

    private fun orderUiEvent(events: OrderUiEvent) {
        when (events) {
            is OrderUiEvent.OrderDeleteSuccess -> _orderUiState.update { it.copy(isDeleteSuccess = events.isDeleteSuccess) }
            is OrderUiEvent.OrderList -> _orderUiState.update { it.copy(orderList = events.orderList) }
            is OrderUiEvent.OrderSuccess -> _orderUiState.update { it.copy(orderSuccess = events.orderSuccess) }
            is OrderUiEvent.ShowError -> _orderUiState.update { it.copy(isError = events.isError) }
            is OrderUiEvent.ShowErrorMessage -> _orderUiState.update { it.copy(errorMessage = events.errorMessage) }
            is OrderUiEvent.ShowLoading -> _orderUiState.update { it.copy(isLoading = events.isLoading) }
            is OrderUiEvent.AreaList -> _orderUiState.update { it.copy(areaList = events.areaList) }
            is OrderUiEvent.CityList -> _orderUiState.update { it.copy(cityList = events.cityList) }
            is OrderUiEvent.GetAddresses -> _orderUiState.update { it.copy(getAddresses = events.getAddresses) }
            is OrderUiEvent.GetAddressById -> _orderUiState.update { it.copy(address = events.address) }
        }
    }

    private fun getAddress(address: ProfileModel) =
        orderUiEvent(OrderUiEvent.GetAddressById(address))

    private fun getAddresses(list: List<ProfileModel>?) =
        orderUiEvent(OrderUiEvent.GetAddresses(list))

    private fun areaList(list: List<String>) = orderUiEvent(OrderUiEvent.AreaList(list))
    private fun cityList(list: List<String>) = orderUiEvent(OrderUiEvent.CityList(list))
    private fun isLoading(loading: Boolean) = orderUiEvent(OrderUiEvent.ShowLoading(loading))
    fun isError(error: Boolean) = orderUiEvent(OrderUiEvent.ShowError(error))
    private fun errorMessage(message: String) = orderUiEvent(OrderUiEvent.ShowErrorMessage(message))
    private fun orderList(list: List<CartModel>) = orderUiEvent(OrderUiEvent.OrderList(list))
    private fun orderSuccess(success: Boolean) = orderUiEvent(OrderUiEvent.OrderSuccess(success))
    private fun orderDeleteSuccess(delete: Boolean) =
        orderUiEvent(OrderUiEvent.OrderDeleteSuccess(delete))

    private fun getAllProducts() = viewModelScope.launch {
        getCartProducts().collect { response ->
            when (response) {
                is Response.Success -> {
                    isLoading(false)
                    orderList(response.data)
                }

                is Response.Error -> {
                    isLoading(false)
                    isError(false)
                    errorMessage(response.e)
                }

                is Response.Loading -> isLoading(true)
            }
        }
    }

    fun placeAllOrders(
        userID: String,
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        totalAmount: String,
        products: List<CartModel>
    ) = viewModelScope.launch {
        placeOrder(
            userID,
            name,
            phoneNumber,
            null,
            address,
            area,
            city,
            null,
            currentDate,
            orderTime,
            date.toString(),
            "In Progress",
            "Cash on Delivery",
            totalAmount,
            products,
        ).collect { response ->
            when (response) {
                is Response.Success -> {
                    isLoading(false)
                    orderSuccess(true)
                    emptyCart()
                }

                is Response.Error -> {
                    isLoading(false)
                    isError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> isLoading(true)
            }
        }
    }

    private fun emptyCart() = viewModelScope.launch {
        deleteAllCart().collect {
            when (it) {
                is Response.Success -> {
                    isLoading(false)
                    orderDeleteSuccess(true)
                }

                is Response.Error -> {
                    isLoading(false)
                    isError(true)
                    errorMessage(it.e)
                }

                is Response.Loading -> isLoading(true)

            }
        }
    }

    private fun fetchAllProfiles() = viewModelScope.launch {
        getAllProfiles().collect { response ->
            when (response) {
                is Response.Success -> {
                    isLoading(false)
                    Log.d("ProfileList", "${response.data}")
                    getAddresses(response.data)
                }

                is Response.Error -> {
                    isLoading(false)
                    isError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> isLoading(true)
            }
        }
    }


    fun fetchProfileById(id: Int) = viewModelScope.launch {
        getProfileDataById(id).collect { response ->
            when (response) {
                is Response.Success -> {
                    isLoading(false)
                    getAddress(response.data)
                }

                is Response.Error -> {
                    isLoading(false)
                    isError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> isLoading(true)
            }
        }
    }

    fun addNewProfile(
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
    ) = viewModelScope.launch {
        addProfile(name, phoneNumber, address, area, city, null).collect { response ->
            when (response) {
                is Response.Success -> {
                    isLoading(false)
                    fetchAllProfiles()
                }

                is Response.Error -> {
                    isLoading(false)
                    isError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> isLoading(true)
            }
        }
    }

    private fun fetchCityList() = viewModelScope.launch {
        getCityList().collect { response ->
            when (response) {
                is Response.Success -> {
                    isLoading(false)
                    cityList(response.data)
                }

                is Response.Error -> {
                    isLoading(false)
                    isError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> isLoading(true)
            }
        }
    }

    fun fetchAreaList(city: String) = viewModelScope.launch {
        getAreaList(city).collect { response ->
            when (response) {
                is Response.Success -> {
                    isLoading(false)
                    areaList(response.data)
                }

                is Response.Error -> {
                    isLoading(false)
                    isError(true)
                    errorMessage(response.e)
                }

                is Response.Loading -> isLoading(true)
            }
        }
    }
}