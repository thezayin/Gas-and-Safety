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

/**
 * Composable function for the Home Screen that displays the home content and manages
 * navigation between different parts of the app (settings, contact, cart, etc.).
 *
 * @param navigateToSetting Function to navigate to the settings screen.
 * @param navigateToContact Function to navigate to the contact screen.
 * @param navigateToCart Function to navigate to the cart screen.
 * @param navigateToHistory Function to navigate to the history screen.
 * @param navigateToProfile Function to navigate to the profile screen.
 * @param navigateToAddress Function to navigate to the address screen.
 * @param navigateToProductScreen Function to navigate to the product details screen, accepting the product ID.
 */
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
    // Inject the HomeViewModel using Koin
    val viewModel: HomeViewModel = koinInject()

    // Get the current activity context
    val activity = LocalContext.current as Activity

    // Remember coroutine scope for launching tasks
    val scope = rememberCoroutineScope()

    // Fetch the user's UUID
    activity.getUserUUID()

    // Collect the UI state from the HomeViewModel
    val state = viewModel.homeUiState.collectAsState().value
    val isLoading = state.isLoading
    val isError = state.isError
    val errorMessage = state.errorMessage ?: "Unexpected Error, Restart App"
    val productList = state.getProducts
    val isAddedToCart = state.isAdded
    val cartList = state.getCart
    val listAddress = state.getAddresses

    // Display the glass UI effect
    GlassComponent()

    // Set system bar colors
    SetBarColors()

    // Display the main home screen content
    HomeScreenContent(
        modifier = Modifier,
        scope = scope,
        isError = isError,
        errorMessage = errorMessage,
        list = productList,
        isLoading = isLoading,
        onErrorClose = { viewModel.emitShowError(false) }, // Close the error dialog
        activity = activity,
        addedToCart = isAddedToCart,
        cartList = cartList,
        onCartClick = {
            // Add product to cart when clicked
            viewModel.addToCart(
                id = it.id ?: "",
                name = it.name ?: "",
                price = it.price ?: "",
                description = it.description ?: "",
                imageUri = it.imageUri.toString(),
            )
        },
        updateCartValue = { viewModel.emitAddedToCart(false) }, // Update the cart state
        navigateToCart = navigateToCart,
        navigateToContact = navigateToContact,
        navigateToHistory = navigateToHistory,
        navigateToProfile = {
            // Navigate to profile or address based on the list of addresses
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