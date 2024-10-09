package com.thezayin.presentation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.framework.extension.getUserUUID
import com.thezayin.presentation.component.HistoryScreenContent
import org.koin.compose.koinInject

/**
 * Composable that represents the History screen where users can view their order history.
 * It includes handling the UI state, user interaction, and invoking the ViewModel to manage data.
 *
 * @param navigateUp Function to handle navigating back to the previous screen.
 * @param navigateToContactUs Function to navigate to the Contact Us screen.
 */
@Composable
fun HistoryScreen(
    navigateUp: () -> Unit,
    navigateToContactUs: () -> Unit
) {
    // Inject the HistoryViewModel using Koin
    val viewModel: HistoryViewModel = koinInject()

    // Collect the UI state from the ViewModel as State to trigger recomposition when the state changes
    val state = viewModel.historyUiState.collectAsState().value

    // Get the current Activity and the user ID using an extension function
    val activity = LocalContext.current as Activity
    val userId = activity.getUserUUID()

    // Extract necessary states from the collected UI state
    val errorMessage = state.errorMessage
    val isLoading = state.isLoading
    val isError = state.isError
    val list = state.list

    // Remember the filtered product list and the index value for filtering
    val productList = remember { mutableStateOf(list) }
    val indexValue = remember { mutableIntStateOf(0) }

    // Handle back press using BackHandler
    BackHandler(onBack = navigateUp)

    // UI components for background and bar colors
    GlassComponent()
    SetBarColors()

    // The main content of the history screen, passing all required parameters to HistoryScreenContent
    HistoryScreenContent(
        userId = userId,
        list = list,
        isLoading = isLoading,
        isError = isError,
        productList = productList,
        errorMessage = errorMessage,
        navigateUp = navigateUp,
        indexValue = indexValue,
        navigateToContactUs = navigateToContactUs,
        onErrorClose = { viewModel.showError(false) },
        getOrdersHistory = { id -> viewModel.getOrdersHistory(id) }
    )
}