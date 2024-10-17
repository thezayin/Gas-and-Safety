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

/**
 * A composable function that represents the main content of a product screen, including an image,
 * product details, and "Add to Cart" functionality.
 *
 * @param modifier Modifier to adjust layout behavior and appearance.
 * @param isLoading Boolean flag indicating if a loading dialog should be displayed.
 * @param isError Boolean flag indicating if an error dialog should be displayed.
 * @param errorMessage The error message to be displayed in case of an error.
 * @param addedToCart Boolean flag to show a snackbar when a product is added to the cart.
 * @param scope CoroutineScope for managing coroutines in the UI.
 * @param imageUri URI of the product image to be displayed.
 * @param price The price of the product.
 * @param productName The name of the product to be displayed.
 * @param productDescription The description of the product.
 * @param navigateUp Lambda function to navigate back.
 * @param navigateToContact Lambda function to navigate to the contact screen.
 * @param onErrorClose Lambda function to close the error dialog.
 * @param onBuyClick Lambda function to handle "Buy" button clicks.
 * @param updateCartValue Lambda function to update the cart state.
 */
@Composable
fun ProductScreenContent(
    modifier: Modifier = Modifier,
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
    // Show error dialog if there is an error
    if (isError) {
        ErrorQueryDialog(
            showDialog = { onErrorClose() },
            callback = {},
            error = errorMessage
        )
    }

    // Show loading dialog while loading
    if (isLoading) {
        LoadingDialog()
    }

    // Show snackbar when an item is added to the cart
    if (addedToCart) {
        RememberSnackBar(
            cartTintColor = R.color.green,
            message = "Added to Cart",
            scope = scope
        ) { boolean ->
            scope.launch {
                updateCartValue(boolean)
            }
        }
    }

    // Main scaffold structure with top bar, content, and bottom bar
    Scaffold(
        modifier = modifier
            .navigationBarsPadding() // Handle navigation bar padding
            .statusBarsPadding(), // Handle status bar padding
        containerColor = colorResource(id = R.color.semi_transparent), // Background color for the screen
        topBar = {
            // Top bar component with back button and contact button
            ProductTopBar(
                modifier = Modifier,
                screen = "", // You can pass screen name or title if needed
                onBackClick = navigateUp, // Handle back navigation
                onContactClick = navigateToContact // Handle navigation to contact screen
            )
        },
        bottomBar = {
            // Bottom bar with a "Buy" button and price
            BuyButton(
                modifier = Modifier,
                price = price, // Display the product price
                onBuyClick = onBuyClick // Handle "Buy" button clicks
            )
        }
    ) { padding ->
        // Column to arrange the product image and details vertically
        Column(modifier = Modifier.padding(padding)) {
            if (imageUri.toString()
                    .isEmpty() || imageUri == null || productName.isEmpty() || productDescription.isEmpty()
            ) {
                return@Column
            }
            // Product image section
            ProductImage(
                modifier = Modifier,
                productImage = imageUri // Display the product image if available
            )

            // Product details section (name and description)
            ProductDetails(
                modifier = Modifier,
                productName = productName, // Display the product name
                productDescription = productDescription // Display the product description
            )
        }
    }
}