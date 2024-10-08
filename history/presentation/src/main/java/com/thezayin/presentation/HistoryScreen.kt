package com.thezayin.presentation

import android.app.Activity
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

@Composable
fun HistoryScreen(
    navigateUp: () -> Unit, navigateToContactUs: () -> Unit
) {
    val viewModel: HistoryViewModel = koinInject()
    val state = viewModel.historyUiState.collectAsState().value
    val activity = LocalContext.current as Activity

    val userId = activity.getUserUUID()

    val errorMessage = state.errorMessage
    val isLoading = state.isLoading
    val isError = state.isError
    val list = state.list

    val productList = remember { mutableStateOf(list) }
    val indexValue = remember { mutableIntStateOf(0) }

    GlassComponent()
    SetBarColors()

    HistoryScreenContent(userId = userId,
        list = list,
        isLoading = isLoading,
        isError = isError,
        productList = productList,
        errorMessage = errorMessage,
        navigateUp = navigateUp,
        indexValue = indexValue,
        navigateToContactUs = navigateToContactUs,
        onErrorClose = { viewModel.showError(false) },
        getOrdersHistory = { id -> viewModel.getOrdersHistory(id) })
}