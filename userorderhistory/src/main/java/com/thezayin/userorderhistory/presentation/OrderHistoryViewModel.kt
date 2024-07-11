package com.thezayin.userorderhistory.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.OrderModel
import com.thezayin.framework.utils.Response
import com.thezayin.userorderhistory.domain.usecase.GetMyOrders
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderHistoryViewModel(private val getMyOrders: GetMyOrders) : ViewModel() {
    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _getOrdersHistory = MutableStateFlow(GetOrderHistoryState())
    val getOrdersHistory = _getOrdersHistory.asStateFlow()

    private val _isQueryError =
        MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    fun getOrdersHistory(userId: String) = viewModelScope.launch {
        getMyOrders(userId).collect { response ->
            when (response) {
                is Response.Success -> {
                    Log.d("response", "${response.data}")
                    _isLoading.update { it.copy(isLoading = false) }
                    _getOrdersHistory.update { it.copy(list = response.data) }
                }

                is Response.Error -> {
                    _isQueryError.update { errorState ->
                        errorState.copy(isError = true, errorMessage = response.e)
                    }
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> _isLoading.update { it.copy(isLoading = true) }
            }
        }
    }

    data class GetOrderHistoryState(val list: List<OrderModel> = emptyList())

}