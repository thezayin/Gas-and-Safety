package com.thezayin.lpg.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.UserTopBar
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.snackbar.RememberSnackBar
import com.thezayin.lpg.R
import com.thezayin.lpg.screens.destinations.ContactUsScreenDestination
import com.thezayin.lpg.screens.destinations.OrderScreenDestination
import com.thezayin.usercart.presentation.CartViewModel
import com.thezayin.usercart.presentation.component.CartBottomBar
import com.thezayin.usercart.presentation.component.CartList
import com.thezayin.usercart.presentation.component.DeleteAllCart
import com.thezayin.usercart.presentation.component.NoProductFound
import org.koin.compose.koinInject

@Composable
@Destination
fun CartScreen(navigator: DestinationsNavigator) {
    val viewModel: CartViewModel = koinInject()
    val productList = viewModel.getProducts.collectAsState().value.list
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val price = viewModel.getProducts.collectAsState().value.list.sumOf { it.totalPrice!!.toInt() }
    val delete = viewModel.isDeleteQuery.collectAsState().value.isSuccess
    val scope = rememberCoroutineScope()

    GlassComponent()

    if (isLoading) {
        LoadingDialog()
    }

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(
                modifier = Modifier,
                screen = "Profile",
                onBackClick = { navigator.navigateUp() },
                onContactClick = { navigator.navigate(ContactUsScreenDestination) }
            )
        },
        bottomBar = {
            if (productList.isNotEmpty()) {
                CartBottomBar(
                    price = price.toString(),
                    modifier = Modifier,
                    onClick = { price ->
                        navigator.navigate(OrderScreenDestination(price))
                    },
                )
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (productList.isEmpty()) {
                NoProductFound()
            } else {
                DeleteAllCart(callback = { viewModel.emptyCart() })
                CartList(
                    modifier = Modifier,
                    productList = productList,
                    onDecrement = { viewModel.decrementQuantity(it) },
                    onIncrement = {
                        viewModel.incrementQuantity(it)
                    }
                )
            }
        }
    }

    if (delete) {
        RememberSnackBar(
            cartTintColor = com.thezayin.core.R.color.red,
            message = "Products Deleted", scope = scope
        ) { boolean ->
            viewModel.deleteQuery(boolean)
        }
    }
}