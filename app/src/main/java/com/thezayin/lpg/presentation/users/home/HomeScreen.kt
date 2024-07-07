package com.thezayin.lpg.presentation.users.home

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.framework.extension.functions.getUserUUID
import com.thezayin.lpg.R
import com.thezayin.lpg.common.component.GlassComponent
import com.thezayin.lpg.common.dialogs.ErrorQueryDialog
import com.thezayin.lpg.common.dialogs.LoadingDialog
import com.thezayin.lpg.common.snackbar.RememberSnackBar
import com.thezayin.lpg.presentation.users.cart.CartViewModel
import com.thezayin.lpg.presentation.users.home.component.HomeBottomBar
import com.thezayin.lpg.presentation.users.home.component.HomeProductList
import com.thezayin.lpg.presentation.users.home.component.TopBarComponent
import com.thezayin.lpg.presentation.users.profile.ProfileViewModel
import org.koin.compose.koinInject

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()

    val viewModel: HomeViewModel = koinInject()
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val productList = viewModel.getProducts.collectAsState().value.list
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage

    val cartViewModel: CartViewModel = koinInject()
    val list = cartViewModel.getProducts.collectAsState().value.list
    val isLoadingCart = cartViewModel.isLoading.collectAsState().value.isLoading
    val addedToCart = cartViewModel.isAddedToCart.collectAsState().value.isAdded

    val profileViewModel: ProfileViewModel = koinInject()
    val profileList = profileViewModel.getProfileList.collectAsState().value.data
    activity.getUserUUID()

    if (isLoading || isLoadingCart) {
        LoadingDialog()
    }

    if (isError) {
        ErrorQueryDialog(
            showDialog = { isError = it },
            callback = {},
            error = errorMessage
        )
    }
    GlassComponent()

    Scaffold(
        modifier = Modifier,
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            TopBarComponent(
                modifier = Modifier,
                navigator = navigator
            )
        },
        bottomBar = {
            HomeBottomBar(
                modifier = Modifier,
                navigator = navigator,
                badgeText = list.size.toString(),
                showBadge = list.isNotEmpty(),
                profileList = profileList
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            HomeProductList(
                modifier = Modifier,
                productList = productList,
                onClick = { cartViewModel.addedToCart(it) }
            )
        }
    }
    if (addedToCart) {
        RememberSnackBar(
            cartTintColor = com.thezayin.core.R.color.green,
            message = "Added to Cart",
            scope = scope
        ) { boolean ->
            cartViewModel.updateCartValue(boolean)
        }
    }
}