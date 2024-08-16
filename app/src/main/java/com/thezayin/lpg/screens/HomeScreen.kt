package com.thezayin.lpg.screens

import android.app.Activity
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.StoreClosedDialog
import com.thezayin.common.snackbar.RememberSnackBar
import com.thezayin.framework.extension.functions.getUserUUID
import com.thezayin.framework.extension.functions.isStoreOpen
import com.thezayin.lpg.R
import com.thezayin.lpg.screens.destinations.AddressScreenDestination
import com.thezayin.lpg.screens.destinations.CartScreenDestination
import com.thezayin.lpg.screens.destinations.ContactUsScreenDestination
import com.thezayin.lpg.screens.destinations.OrderHistoryScreenDestination
import com.thezayin.lpg.screens.destinations.ProfileScreenDestination
import com.thezayin.lpg.screens.destinations.SettingScreenDestination
import com.thezayin.useraddress.presentation.ProfileViewModel
import com.thezayin.usercart.presentation.CartViewModel
import com.thezayin.userhome.presentation.HomeViewModel
import com.thezayin.userhome.presentation.component.HomeBottomBar
import com.thezayin.userhome.presentation.component.HomeProductList
import com.thezayin.userhome.presentation.component.TopBarComponent
import org.koin.compose.koinInject

@RequiresApi(Build.VERSION_CODES.O)
@Destination()
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

    val storeOpeningTime = 7 // 7:00 AM
    val storeClosingTime = 19 // 7:00 PM
    var showDialog by remember { mutableStateOf(!isStoreOpen(storeOpeningTime, storeClosingTime)) }

    if (showDialog) {
        StoreClosedDialog(
            onDismiss = { showDialog = false },
            showDialog = {
                // Logic for placing the order even when the store is closed
                showDialog = false
            }
        )
    }
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
    SetBarColors()

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            TopBarComponent(
                modifier = Modifier,
                navigateToSetting = { navigator.navigate(SettingScreenDestination) },
                navigateToContact = { navigator.navigate(ContactUsScreenDestination) }
            )
        },
        bottomBar = {
            HomeBottomBar(
                modifier = Modifier,
                badgeText = list.size.toString(),
                showBadge = list.isNotEmpty(),
                profileList = profileList,
                navigateToHistory = { navigator.navigate(OrderHistoryScreenDestination) },
                navigateToCart = { navigator.navigate(CartScreenDestination) },
                navigateToProfile = { navigator.navigate(ProfileScreenDestination) },
                navigateToAddress = { navigator.navigate(AddressScreenDestination) }
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

    BackHandler {
        activity.finish()
    }
}

