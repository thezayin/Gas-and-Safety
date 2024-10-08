package com.thezayin.presentation.component

import android.net.Uri
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProductScreenContent(
    modifier: Modifier,
    isLoading: Boolean,
    isError: Boolean,
    errorMessage: String,
    addedToCart: Boolean,
    scope: CoroutineScope,
    imageUri: Uri?,
    price: String,
    productName: String,
    productDescription: String,
    navigateUp: () -> Unit,
    navigateToContact: () -> Unit,
    onErrorClose: () -> Unit,
    onBuyClick: () -> Unit,
    updateCartValue: (Boolean) -> Unit,
) {

    if (isError) {
        ErrorQueryDialog(showDialog = { onErrorClose() }, callback = {}, error = errorMessage)
    }

    if (isLoading) {
        LoadingDialog()
    }

    if (addedToCart) {
        RememberSnackBar(
            cartTintColor = R.color.green, message = "Added to Cart", scope = scope
        ) { boolean ->
            scope.launch {
                updateCartValue(boolean)
            }
        }
    }

    Scaffold(modifier = modifier.navigationBarsPadding().statusBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            ProductTopBar(
                modifier = Modifier,
                screen = "",
                onBackClick = navigateUp,
                onContactClick = navigateToContact
            )
        },
        bottomBar = {
            BuyButton(
                modifier = Modifier,
                price = price,
                onBuyClick = onBuyClick
            )
        }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            ProductImage(
                modifier = Modifier,
                productImage = imageUri
            )
            ProductDetails(
                modifier = Modifier,
                productName = productName,
                productDescription = productDescription
            )
        }
    }
}