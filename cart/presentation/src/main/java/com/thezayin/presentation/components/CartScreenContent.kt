package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.assets.R
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.snackbar.RememberSnackBar
import com.thezayin.databases.model.CartModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun CartScreenContent(
    modifier: Modifier,
    price: String,
    isError: Boolean,
    isLoading: Boolean,
    errorMessage: String,
    list: List<CartModel>?,
    isProductRemoved: Boolean,
    scope: CoroutineScope,
    emptyCart: () -> Unit,
    onBackClick: () -> Unit,
    onErrorClose: () -> Unit,
    onContactClick: () -> Unit,
    deleteQuery: (Boolean) -> Unit,
    onBuyClick: (String) -> Unit,
    onIncrement: (CartModel) -> Unit,
    onDecrement: (CartModel) -> Unit,
) {
    if (isError) {
        ErrorQueryDialog(showDialog = { onErrorClose() }, callback = {}, error = errorMessage)
    }

    if (isLoading) {
        LoadingDialog()
    }

    Scaffold(modifier = modifier.navigationBarsPadding().statusBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            CartTopBar(
                modifier = Modifier,
                onBackClick = onBackClick,
                onContactClick = onContactClick
            )
        },
        bottomBar = {
            if (list?.isNotEmpty() == true) {
                CartBottomBar(
                    price = price,
                    modifier = Modifier,
                    onBuyClick = onBuyClick,
                )
            }
        }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (list != null) {
                if (list.isEmpty()) {
                    NoProductFound()
                } else {
                    DeleteAllCart(emptyClick = emptyCart)
                    CartList(
                        modifier = Modifier,
                        productList = list,
                        onDecrement = onDecrement,
                        onIncrement = onIncrement
                    )
                }
            }
        }
    }
    if (isProductRemoved) {
        RememberSnackBar(
            cartTintColor = R.color.red,
            message = "Products Deleted",
            scope = scope
        ) { boolean ->
            deleteQuery(boolean)
        }
    }
}