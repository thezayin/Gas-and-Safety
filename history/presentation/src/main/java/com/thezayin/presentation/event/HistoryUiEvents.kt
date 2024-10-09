package com.thezayin.presentation.event

import com.thezayin.domain.model.OrderModel

/**
 * Sealed interface representing different UI events related to the order history screen.
 * These events capture changes in UI state, such as loading, error, and displaying the orders list.
 */
sealed interface HistoryUiEvents {

    /**
     * Event triggered when there is an error message to display.
     *
     * @param errorMessage The error message string that needs to be shown in the UI.
     */
    data class ErrorMessage(val errorMessage: String) : HistoryUiEvents

    /**
     * Event triggered to show or hide the loading state in the UI.
     *
     * @param isLoading Boolean flag indicating whether the UI should display a loading indicator.
     */
    data class ShowLoading(val isLoading: Boolean) : HistoryUiEvents

    /**
     * Event triggered to show or hide the error state in the UI.
     *
     * @param isError Boolean flag indicating whether an error occurred that needs to be displayed.
     */
    data class ShowError(val isError: Boolean) : HistoryUiEvents

    /**
     * Event triggered when the list of orders has been successfully fetched.
     *
     * @param list The list of OrderModel objects that represent the fetched orders.
     */
    data class OrdersList(val list: List<OrderModel>) : HistoryUiEvents
}
