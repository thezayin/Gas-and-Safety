package com.thezayin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.domain.model.OrderModel
import com.thezayin.domain.usecase.GetMyOrders
import com.thezayin.framework.utils.Response
import com.thezayin.presentation.event.HistoryUiEvents
import com.thezayin.presentation.state.HistoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(private val getMyOrders: GetMyOrders) : ViewModel() {


    private val _historyUiState = MutableStateFlow(HistoryUiState())
    val historyUiState = _historyUiState.asStateFlow()

    private fun historyUiEvents(events: HistoryUiEvents) {
        when (events) {
            is HistoryUiEvents.ErrorMessage -> _historyUiState.update { it.copy(errorMessage = events.errorMessage) }
            is HistoryUiEvents.OrdersList -> _historyUiState.update { it.copy(list = events.list) }
            is HistoryUiEvents.ShowError -> _historyUiState.update { it.copy(isError = events.isError) }
            is HistoryUiEvents.ShowLoading -> _historyUiState.update { it.copy(isLoading = events.isLoading) }
        }
    }

    private fun ordersList(list: List<OrderModel>) =
        historyUiEvents(HistoryUiEvents.OrdersList(list))

    private fun showLoading(isLoading: Boolean) =
        historyUiEvents(HistoryUiEvents.ShowLoading(isLoading))

    fun showError(isError: Boolean) = historyUiEvents(HistoryUiEvents.ShowError(isError))
    private fun errorMessage(errorMessage: String) =
        historyUiEvents(HistoryUiEvents.ErrorMessage(errorMessage))

    fun getOrdersHistory(userId: String) = viewModelScope.launch {
        getMyOrders(userId).collect { response ->
            when (response) {
                is Response.Success -> {
                    showLoading(false)
                    ordersList(response.data)
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