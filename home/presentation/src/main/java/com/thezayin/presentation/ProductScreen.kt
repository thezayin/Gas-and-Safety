package com.thezayin.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.presentation.component.ProductScreenContent
import org.koin.compose.koinInject

@Composable
fun ProductScreen(
    productId: String,
    navigateUp: () -> Unit,
    navigateToContact: () -> Unit,
) {
    val viewModel: HomeViewModel = koinInject()
    val state = viewModel.homeUiState.collectAsState().value
    val scope = rememberCoroutineScope()

    val isLoading = state.isLoading
    val isError = state.isError
    val errorMessage = state.errorMessage
    val productDetail = state.productDetail
    val isAddedToCart = state.isAdded

    LaunchedEffect(Unit) {
        viewModel.fetchProduct(productId)
    }

    GlassComponent()
    SetBarColors()

    BackHandler(onBack = navigateUp)

    ProductScreenContent(
        modifier = Modifier,
        navigateUp = navigateUp,
        imageUri = productDetail?.imageUri,
        productName = productDetail?.name ?: "No Data Found",
        isError = isError,
        addedToCart = isAddedToCart,
        isLoading = isLoading,
        scope = scope,
        errorMessage = errorMessage ?: "Unexpected Error, Restart App",
        productDescription = productDetail?.description ?: "No Data Found",
        navigateToContact = navigateToContact,
        price = productDetail?.price ?: "No Data Found",
        onBuyClick = {
            viewModel.addToCart(
                id = productDetail?.id ?: "",
                name = productDetail?.name ?: "",
                price = productDetail?.price ?: "",
                description = productDetail?.description ?: "",
                imageUri = productDetail?.imageUri.toString(),
            )
        },
        onErrorClose = { viewModel.emitShowError(false) },
        updateCartValue = { viewModel.emitAddedToCart(false) },
    )
}