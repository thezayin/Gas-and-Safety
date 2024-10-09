package com.thezayin.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp

/**
 * CartActionBar displays the total price and a button to proceed with the purchase.
 *
 * @param modifier Modifier to customize the layout.
 * @param onPurchaseClick Callback function to execute when the buy button is clicked, passing the total price.
 * @param totalPrice The total price to be displayed in the action bar.
 */
@Composable
fun CartActionBar(
    modifier: Modifier,
    onPurchaseClick: (String) -> Unit = {},
    totalPrice: String
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 20.sdp, topEnd = 20.sdp))
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxWidth()
    ) {
        // Display the total price of items in the cart
        TotalPrice(price = totalPrice)

        // Button to proceed to purchase
        CartActionButton(modifier = Modifier) {
            onPurchaseClick(totalPrice)
        }
    }
}

@Composable
@Preview
fun CartActionBarPreview() {
    CartActionBar(modifier = Modifier, onPurchaseClick = {}, totalPrice = "10000")
}