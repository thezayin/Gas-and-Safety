package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.thezayin.databases.model.CartModel
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable that displays a list of products in the order.
 * Each product is shown with its name, quantity, and price.
 *
 * @param orders A list of CartModel objects representing the ordered products.
 */
@Composable
fun OrderProductList(
    orders: List<CartModel>
) {
    // A LazyColumn to efficiently display a scrollable list of products in the order.
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Iterating over the list of orders and displaying each product with its details.
        items(orders.size) { index ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Display product name and quantity
                Text(
                    text = "${orders[index].name} x ${orders[index].quantity}",
                    color = colorResource(id = com.thezayin.assets.R.color.black),
                    fontSize = 8.ssp,
                    fontFamily = FontFamily(Font(com.thezayin.assets.R.font.noto_sans_bold)),
                )

                // Display product price, defaulting to 0 if not available
                Text(
                    text = "Rs. ${orders[index].price ?: "0"}",
                    fontSize = 8.ssp,
                    fontFamily = FontFamily(Font(com.thezayin.assets.R.font.noto_sans_bold)),
                    color = colorResource(id = com.thezayin.assets.R.color.black)
                )
            }
        }
    }
}