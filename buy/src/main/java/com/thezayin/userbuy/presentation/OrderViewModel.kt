package com.thezayin.userbuy.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.entities.CartModel
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.GetSuccessState
import com.thezayin.framework.utils.Response
import com.thezayin.userbuy.domain.usecase.PlaceOrder
import com.thezayin.usercart.domain.usecase.DeleteAllCart
import com.thezayin.usercart.domain.usecase.GetCartProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

class OrderViewModel(
    private val placeOrder: PlaceOrder,
    private val getCartProducts: GetCartProducts,
    private val deleteAllCart: DeleteAllCart,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _isQuerySuccessful = MutableStateFlow(GetSuccessState())
    val isQuerySuccessful = _isQuerySuccessful.asStateFlow()

    private val _getProducts = MutableStateFlow(GetProductState())
    val getProducts = _getProducts.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("dd/M/yyyy")
    private val currentDate: String = sdf.format(Date())
    private val orderTime: String = SimpleDateFormat("HH:mm:ss").format(Date())
    val date = LocalDateTime.now()

    init {
        getAllProducts()
    }

    private fun getAllProducts() = viewModelScope.launch {
        getCartProducts().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    _getProducts.update {
                        it.copy(list = response.data)
                    }
                }

                is Response.Error -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    _isQueryError.update {
                        it.copy(
                            isError = true, errorMessage = response.e
                        )
                    }
                }

                is Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun placeAllOrders(
        userID: String,
        name: String,
        phoneNumber: String,
        email: String?,
        address: String,
        area: String,
        city: String,
        message: String?,
        totalAmount: String,
    ) = viewModelScope.launch {
        placeOrder(
            userID,
            name,
            phoneNumber,
            email,
            address,
            area,
            city,
            message,
            currentDate,
            orderTime,
            date.toString(),
            "In Progress",
            "Cash on Delivery",
            totalAmount,
            getProducts.value.list,
        ).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    emptyCart()
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update {
                        it.copy(isError = true, errorMessage = response.e)
                    }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun emptyCart() = viewModelScope.launch {
        deleteAllCart().collect {
            when (it) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQuerySuccessful.update { it.copy(isSuccess = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update {
                        it.copy(isError = true, errorMessage = it.errorMessage)
                    }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    data class GetProductState(val list: List<CartModel> = emptyList())
}