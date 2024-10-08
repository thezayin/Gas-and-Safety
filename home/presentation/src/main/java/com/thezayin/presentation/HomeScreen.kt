package com.thezayin.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.framework.extension.getUserUUID
import com.thezayin.presentation.component.HomeScreenContent
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navigateToSetting: () -> Unit,
    navigateToContact: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToHistory: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToAddress: () -> Unit,
    navigateToProductScreen: (String) -> Unit,
) {
    val viewModel: HomeViewModel = koinInject()
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()

    activity.getUserUUID()

    val state = viewModel.homeUiState.collectAsState().value
    val isLoading = state.isLoading
    val isError = state.isError
    val errorMessage = state.errorMessage
    val list = state.getProducts
    val isAddedToCart = state.isAdded
    val cartList = state.getCart
    val listAddress = state.getAddresses

    GlassComponent()
    SetBarColors()

    HomeScreenContent(
        modifier = Modifier,
        scope = scope,
        isError = isError,
        errorMessage = errorMessage ?: "Unexpected Error, Restart App",
        list = list,
        isLoading = isLoading,
        onErrorClose = { viewModel.emitShowError(false) },
        activity = activity,
        addedToCart = isAddedToCart,
        cartList = cartList,
        onCartClick = {
            viewModel.addToCart(
                id = it.id ?: "",
                name = it.name ?: "",
                price = it.price ?: "",
                description = it.description ?: "",
                imageUri = it.imageUri.toString(),
            )
        },
        updateCartValue = { viewModel.emitAddedToCart(false) },
        navigateToCart = navigateToCart,
        navigateToContact = navigateToContact,
        navigateToHistory = navigateToHistory,
        navigateToProfile = {
            if (listAddress.isNullOrEmpty()) {
                navigateToProfile()
            } else {
                navigateToAddress()
            }
        },
        navigateToSetting = navigateToSetting,
        onProductClick = navigateToProductScreen
    )
}