package com.thezayin.presentation.state

import com.thezayin.domain.model.OrderModel

/**
 * Data class representing the UI state for the order history screen.
 * It holds information about the current state of loading, error, and the list of orders retrieved.
 */
data class HistoryUiState(
    val isLoading: Boolean = false,           // Indicates whether the data is currently being loaded
    val isError: Boolean = false,             // Indicates if an error has occurred
    val errorMessage: String = "",            // Holds the error message in case of a failure
    val list: List<OrderModel> = emptyList()  // List of orders (empty if not yet loaded or in case of error)
)