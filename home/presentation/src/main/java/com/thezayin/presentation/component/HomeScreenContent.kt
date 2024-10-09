package com.thezayin.presentation.component

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
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

/**
 * A composable function that displays the main content for the home screen.
 *
 * @param modifier Modifier to adjust layout behavior and appearance.
 * @param scope CoroutineScope for managing coroutines within the UI.
 * @param isLoading Boolean flag to indicate if loading dialog should be shown.
 * @param isError Boolean flag to indicate if an error dialog should be shown.
 * @param errorMessage The error message to display in case of error.
 * @param onErrorClose Lambda function to handle the closing of the error dialog.
 * @param activity The current activity, used to handle the back button press.
 * @param addedToCart Boolean flag indicating if an item has been added to the cart.
 * @param navigateToCart Lambda function to navigate to the cart screen.
 * @param navigateToHistory Lambda function to navigate to the history screen.
 * @param navigateToProfile Lambda function to navigate to the profile screen.
 * @param navigateToSetting Lambda function to navigate to the settings screen.
 * @param navigateToContact Lambda function to navigate to the contact screen.
 * @param list List of products to be displayed on the home screen.
 * @param onCartClick Lambda function to handle adding an item to the cart.
 * @param updateCartValue Lambda function to update the cart status when an item is added.
 * @param cartList List of items in the cart, used to display the cart badge.
 * @param onProductClick Lambda function to handle product click events.
 */
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
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
    // Display error dialog if there's an error
    if (isError) {
        ErrorQueryDialog(showDialog = { onErrorClose() }, callback = {}, error = errorMessage)
    }

    // Display loading dialog while loading
    if (isLoading) {
        LoadingDialog()
    }

    // Show snackbar when an item is added to the cart
    if (addedToCart) {
        RememberSnackBar(
            cartTintColor = R.color.green, message = "Added to Cart", scope = scope
        ) { boolean ->
            updateCartValue(boolean)
        }
    }

    // Main scaffold structure with top bar, bottom bar, and content area
    Scaffold(
        modifier = modifier
            .navigationBarsPadding() // Handle navigation bar padding
            .statusBarsPadding(), // Handle status bar padding
        containerColor = colorResource(id = R.color.semi_transparent), // Background color for the scaffold
        topBar = {
            // Top bar component for navigation to settings and contact
            TopBarComponent(
                modifier = Modifier,
                navigateToSetting = navigateToSetting,
                navigateToContact = navigateToContact
            )
        },
        bottomBar = {
            // Bottom bar with cart, history, and profile buttons
            HomeBottomBar(
                modifier = Modifier,
                badgeText = cartList?.size.toString(), // Show the number of items in the cart
                showBadge = cartList.isNullOrEmpty().not(), // Show badge if the cart has items
                navigateToHistory = navigateToHistory, // Navigate to history screen
                navigateToCart = navigateToCart, // Navigate to cart screen
                navigateToProfile = navigateToProfile // Navigate to profile screen
            )
        }
    ) { padding ->
        // Column containing the list of products
        Column(modifier = Modifier.padding(padding)) {
            HomeProductList(
                modifier = Modifier,
                productList = list, // List of products to display
                onCartClick = onCartClick, // Handle adding products to the cart
                onProductClick = onProductClick // Handle clicking on a product
            )
        }
    }

    // Handle the back button press to close the activity
    BackHandler {
        activity.finish()
    }
}