package com.thezayin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.domain.model.OrderModel
import com.thezayin.domain.usecase.GetUserOrders
import com.thezayin.framework.utils.Resource
import com.thezayin.presentation.event.HistoryUiEvents
import com.thezayin.presentation.state.HistoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing the UI state and events for the order history screen.
 * It interacts with the GetUserOrders use case to fetch orders and updates the UI state accordingly.
 *
 * @param getUserOrders The use case that provides the logic to fetch the user's orders.
 */
class HistoryViewModel(private val getUserOrders: GetUserOrders) : ViewModel() {

    // Backing field for the UI state, which is updated based on events
    private val _historyUiState = MutableStateFlow(HistoryUiState())

    // Public read-only access to the UI state, exposed as a StateFlow for observing UI changes
    val historyUiState = _historyUiState.asStateFlow()

    /**
     * Handles various UI events by updating the state accordingly.
     * It uses a sealed interface to manage loading, error, and order data events.
     *
     * @param events The UI event to handle (e.g., ShowLoading, ShowError, OrdersList, ErrorMessage).
     */
    private fun historyUiEvents(events: HistoryUiEvents) {
        when (events) {
            is HistoryUiEvents.ErrorMessage -> _historyUiState.update { it.copy(errorMessage = events.errorMessage) }
            is HistoryUiEvents.OrdersList -> _historyUiState.update { it.copy(list = events.list) }
            is HistoryUiEvents.ShowError -> _historyUiState.update { it.copy(isError = events.isError) }
            is HistoryUiEvents.ShowLoading -> _historyUiState.update { it.copy(isLoading = events.isLoading) }
        }
    }

    /**
     * Updates the UI state with the list of orders retrieved.
     *
     * @param list The list of orders to be displayed in the UI.
     */
    private fun ordersList(list: List<OrderModel>) =
        historyUiEvents(HistoryUiEvents.OrdersList(list))

    /**
     * Updates the UI state to reflect whether the loading indicator should be shown or hidden.
     *
     * @param isLoading Boolean flag to show (true) or hide (false) the loading spinner.
     */
    private fun showLoading(isLoading: Boolean) =
        historyUiEvents(HistoryUiEvents.ShowLoading(isLoading))

    /**
     * Updates the UI state to indicate whether an error occurred.
     *
     * @param isError Boolean flag indicating if an error occurred (true) or not (false).
     */
    fun showError(isError: Boolean) = historyUiEvents(HistoryUiEvents.ShowError(isError))

    /**
     * Updates the UI state with an error message.
     *
     * @param errorMessage The error message to be displayed in the UI.
     */
    private fun errorMessage(errorMessage: String) =
        historyUiEvents(HistoryUiEvents.ErrorMessage(errorMessage))

    /**
     * Fetches the order history for a specific user and updates the UI state based on the response.
     *
     * @param userId The unique ID of the user whose order history is being fetched.
     */
    fun getOrdersHistory(userId: String) = viewModelScope.launch {
        getUserOrders(userId).collect { response ->
            when (response) {
                is Resource.Success -> {
                    showLoading(false)
                    ordersList(response.data)
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