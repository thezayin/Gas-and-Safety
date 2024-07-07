package com.thezayin.lpg.presentation.admin.fetchOrders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.model.FetchOrdersModel
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.model.OrderStatusModel
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase.FetchOrders
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase.GetStatusList
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.usecase.UpdateOrderStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FetchOrdersViewModel(
    private val fetchOrders: FetchOrders,
    private val getStatusList: GetStatusList,
    private val updateOrderStatus: UpdateOrderStatus
) : ViewModel() {

    private val _userOrders = MutableStateFlow(GetUserOrdersState())
    val userOrders = _userOrders.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _isQueryError =
        MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isQuerySuccessful =
        MutableStateFlow(GetSuccessState())
    val isQuerySuccessful = _isQuerySuccessful.asStateFlow()

    private val _orderStatusList = MutableStateFlow(GetStatusState())
    val orderStatusList = _orderStatusList.asStateFlow()

    private val _updateStatus = MutableStateFlow(UpdateStatusState())
    val updateStatus = _updateStatus.asStateFlow()

    init {
        getUserOrders()
    }

    private fun getOrderStatusList() = viewModelScope.launch {
        getStatusList().collect { response ->
            when (response) {
                is Response.Error -> {
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Success -> {
                    _orderStatusList.update { it.copy(list = response.data) }
                }

                Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun updateStatus(status: String) = viewModelScope.launch {
        updateOrderStatus(status).collect { response ->
            when (response) {
                is Response.Error -> {
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                    _updateStatus.update { it.copy(isSuccess = false) }
                }

                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _updateStatus.update { it.copy(isSuccess = response.data) }
                }

                Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun getUserOrders() = viewModelScope.launch {
        fetchOrders().collect { response ->
            when (response) {
                is Response.Error -> {
                    _isQueryError.update {
                        it.copy(isError = true, errorMessage = response.e)
                    }
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                }

                is Response.Success -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                    _userOrders.update {
                        it.copy(list = response.data)
                    }
                }

                Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }

    data class GetUserOrdersState(val list: List<FetchOrdersModel> = emptyList())
    data class GetStatusState(val list: List<OrderStatusModel> = emptyList())
    data class UpdateStatusState(val isSuccess: Boolean = false)
    data class GetLoadingState(val isLoading: Boolean = false)
    data class GetSuccessState(val isSuccess: Boolean = false)
    data class GetErrorState(val isError: Boolean = false, val errorMessage: String = "")
}