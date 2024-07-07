package com.thezayin.lpg.presentation.users.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.lpg.R
import com.thezayin.lpg.common.component.GlassComponent
import com.thezayin.lpg.common.component.UserTopBar
import com.thezayin.lpg.common.dialogs.LoadingDialog
import com.thezayin.lpg.common.snackbar.RememberSnackBar
import com.thezayin.lpg.presentation.users.cart.component.CartBottomBar
import com.thezayin.lpg.presentation.users.cart.component.CartList
import com.thezayin.lpg.presentation.users.cart.component.DeleteAllCart
import com.thezayin.lpg.presentation.users.cart.component.NoProductFound
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
        modifier = Modifier,
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(
                modifier = Modifier,
                navigator = navigator,
                screen = "Cart"
            )
        },
        bottomBar = {
            if (productList.isNotEmpty()) {
                CartBottomBar(modifier = Modifier, navigator = navigator, price = price.toString())
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
                    cartViewModel = viewModel,
                    productList = productList
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