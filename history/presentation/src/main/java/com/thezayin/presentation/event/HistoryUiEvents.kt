package com.thezayin.presentation.event

import com.thezayin.domain.model.OrderModel

sealed interface HistoryUiEvents {
    data class ErrorMessage(val errorMessage: String) : HistoryUiEvents
    data class ShowLoading(val isLoading: Boolean) : HistoryUiEvents
    data class ShowError(val isError: Boolean) : HistoryUiEvents
    data class OrdersList(val list: List<OrderModel>) : HistoryUiEvents
}