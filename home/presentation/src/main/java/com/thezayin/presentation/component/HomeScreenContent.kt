package com.thezayin.presentation.component

import android.app.Activity
import androidx.activity.compose.BackHandler
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
import com.thezayin.domain.model.HomeProdModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    scope: CoroutineScope,
    isLoading: Boolean,
    isError: Boolean,
    errorMessage: String,
    onErrorClose: () -> Unit,
    activity: Activity,
    addedToCart: Boolean,
    navigateToCart: () -> Unit,
    navigateToHistory: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToSetting: () -> Unit,
    navigateToContact: () -> Unit,
    list: List<HomeProdModel>?,
    onCartClick: (HomeProdModel) -> Unit,
    updateCartValue: (Boolean) -> Unit,
    cartList: List<CartModel>?,
    onProductClick: (String) -> Unit
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
            updateCartValue(boolean)
        }
    }

    Scaffold(
        modifier = modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            TopBarComponent(
                modifier = Modifier,
                navigateToSetting = navigateToSetting,
                navigateToContact = navigateToContact
            )
        },
        bottomBar = {
            HomeBottomBar(
                modifier = Modifier,
                badgeText = cartList?.size.toString(),
                showBadge = cartList.isNullOrEmpty().not(),
                navigateToHistory = navigateToHistory,
                navigateToCart = navigateToCart,
                navigateToProfile = navigateToProfile,
            )
        }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            HomeProductList(
                modifier = Modifier,
                productList = list,
                onCartClick = onCartClick,
                onProductClick = onProductClick
            )
        }
    }

    BackHandler {
        activity.finish()
    }
}