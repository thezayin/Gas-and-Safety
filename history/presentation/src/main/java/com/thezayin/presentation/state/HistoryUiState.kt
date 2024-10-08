package com.thezayin.presentation.state

import com.thezayin.domain.model.OrderModel

data class HistoryUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val list: List<OrderModel> = emptyList()
)