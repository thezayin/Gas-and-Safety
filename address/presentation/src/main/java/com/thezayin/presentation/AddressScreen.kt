package com.thezayin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.thezayin.presentation.components.AddressScreenContent
import org.koin.compose.koinInject

@Composable
fun AddressScreen(
    navigateBack: () -> Unit,
    navigateToContactUs: () -> Unit,
    navigateToProfile: () -> Unit
) {
    val viewModel: ProfileViewModel = koinInject()
    val state = viewModel.addressUiState.collectAsState().value

    val isLoading = state.isLoading
    val isError = state.isError
    val errorMessage = state.errorMessage
    val list = state.getAddresses

    AddressScreenContent(
        isError = isError,
        isLoading = isLoading,
        errorMessage = errorMessage,
        list = list,
        hideError = { viewModel.showError(false) },
        navigateToContactUs = navigateToContactUs,
        navigateBack = navigateBack,
        navigateToProfile = navigateToProfile,
        deleteProfile = {
            viewModel.deleteProfile(it)
        }
    )
}