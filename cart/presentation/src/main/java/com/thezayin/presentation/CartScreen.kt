package com.thezayin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.presentation.components.CartScreenContent
import org.koin.compose.koinInject

@Composable
fun CartScreen(
    onBackClick: () -> Unit,
    onContactClick: () -> Unit,
    onBuyClick: (String) -> Unit
) {
    val viewModel: CartViewModel = koinInject()
    val state = viewModel.cartUiState.collectAsState().value
    val scope = rememberCoroutineScope()

    val isLoading = state.isLoading
    val isError = state.isError
    val errorMessage = state.errorMessage
    val list = state.getProducts
    val isProductRemoved = state.productRemoved
    val price = list?.sumOf { it.totalPrice!!.toInt() }.toString()
    GlassComponent()
    SetBarColors()

    CartScreenContent(
        list = list,
        scope = scope,
        price = price,
        isError = isError,
        modifier = Modifier,
        isLoading = isLoading,
        isProductRemoved = isProductRemoved,
        errorMessage = errorMessage ?: " Unexpected Error",
        onBuyClick = onBuyClick,
        onBackClick = onBackClick,
        onContactClick = onContactClick,
        emptyCart = { viewModel.emptyCart() },
        deleteQuery = { viewModel.removedFromCart(it) },
        onIncrement = { viewModel.incrementQuantity(it) },
        onDecrement = { viewModel.decrementQuantity(it) },
        onErrorClose = { viewModel.showError(false) },
    )
}