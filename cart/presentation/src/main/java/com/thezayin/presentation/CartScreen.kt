package com.thezayin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.presentation.components.CartScreenContent
import org.koin.compose.koinInject

@Composable
fun CartScreen(
    onBackClick: () -> Unit, onContactClick: () -> Unit, onBuyClick: (String) -> Unit
) {
    // Inject the ViewModel using Koin
    val viewModel: CartViewModel = koinInject()

    // Collect the state from the ViewModel
    val state = viewModel.cartUiState.collectAsState().value
    val scope = rememberCoroutineScope()

    // Extract state variables
    val isLoading = state.isLoading
    val isError = state.isError
    val errorMessage = state.errorMessage ?: "Unexpected Error"
    val cartProducts = state.cartProducts ?: emptyList() // Ensure non-null list
    val isProductRemoved = state.isProductRemoved
    val totalPrice = cartProducts.sumOf { it.totalPrice }.toString() // Sum the total price

    // Setup UI components
    GlassComponent()
    SetBarColors()

    // Log the Cart Viewed event
    viewModel.analytics.logEvent(AnalyticsEvent.CartViewedEvent())

    // Render the CartScreenContent with appropriate parameters
    CartScreenContent(
        modifier = Modifier,
        price = totalPrice,
        isError = isError,
        isLoading = isLoading,
        errorMessage = errorMessage,
        list = cartProducts,
        isProductRemoved = isProductRemoved,
        scope = scope,
        emptyCart = {
            viewModel.clearCart()
            viewModel.analytics.logEvent(AnalyticsEvent.CartClearedEvent()) // Log cart cleared event
        }, // Use clearCart to empty the cart
        onBackClick = onBackClick,
        onErrorClose = { viewModel.showError(false) },
        onContactClick = onContactClick,
        deleteQuery = {
            viewModel.removedFromCart(it)
            if (it) {
                // Log item removed event
                cartProducts.forEach { product ->
                    viewModel.analytics.logEvent(AnalyticsEvent.CartItemRemovedEvent(product.localId.toString()))
                }
            }
        }, // Use removeProduct to delete an item
        onBuyClick = onBuyClick,
        onIncrement = { viewModel.incrementQuantity(it) }, // Increment product quantity
        onDecrement = { viewModel.decrementQuantity(it) }, // Decrement product quantity
    )
}
