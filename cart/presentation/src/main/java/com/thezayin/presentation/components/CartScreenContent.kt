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

/**
 * Composable function for the Cart screen content.
 *
 * This function manages the display of cart items, loading states, error messages, and interactions.
 *
 * @param modifier Modifier for styling and layout.
 * @param price The total price of items in the cart.
 * @param isError Boolean flag indicating if there is an error.
 * @param isLoading Boolean flag indicating if a loading operation is in progress.
 * @param errorMessage The error message to display if an error occurs.
 * @param list The list of cart items.
 * @param isProductRemoved Boolean flag indicating if a product has been removed from the cart.
 * @param scope CoroutineScope for launching coroutines.
 * @param emptyCart Callback to clear the cart.
 * @param onBackClick Callback for the back button action.
 * @param onErrorClose Callback to close the error dialog.
 * @param onContactClick Callback for contact action.
 * @param deleteQuery Callback to handle deletion confirmation.
 * @param onBuyClick Callback for the buy button action.
 * @param onIncrement Callback for incrementing product quantity.
 * @param onDecrement Callback for decrementing product quantity.
 */
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
    // Display error dialog if there's an error
    if (isError) {
        ErrorQueryDialog(showDialog = { onErrorClose() }, callback = {}, error = errorMessage)
    }

    // Show loading dialog if data is being fetched
    if (isLoading) {
        LoadingDialog()
    }

    // Main scaffold for the cart screen layout
    Scaffold(modifier = modifier.navigationBarsPadding().statusBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            CartTopBar(
                modifier = Modifier, onBackClick = onBackClick, onContactClick = onContactClick
            )
        },
        bottomBar = {
            if (list?.isNotEmpty() == true) {
                CartActionBar(
                    modifier = Modifier, totalPrice = price, onPurchaseClick = onBuyClick
                )
            }
        }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Display content based on cart items
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

    // Display snackbar notification when a product is removed from the cart
    if (isProductRemoved) {
        RememberSnackBar(
            cartTintColor = R.color.red, message = "Product Deleted", scope = scope
        ) { boolean ->
            deleteQuery(boolean)
        }
    }
}
