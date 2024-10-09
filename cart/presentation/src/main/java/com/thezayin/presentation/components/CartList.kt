package com.thezayin.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.databases.model.CartModel
import ir.kaaveh.sdpcompose.sdp

/**
 * CartList displays a list of products currently in the cart.
 *
 * This component utilizes a LazyColumn to efficiently render the list of cart products.
 *
 * @param modifier Optional [Modifier] to customize the layout and appearance.
 * @param productList List of [CartModel] representing the products in the cart.
 * @param onIncrement Callback function invoked when the increment button is clicked on a product.
 * @param onDecrement Callback function invoked when the decrement button is clicked on a product.
 */
@Composable
fun CartList(
    modifier: Modifier,
    productList: List<CartModel>,
    onIncrement: (CartModel) -> Unit = {},
    onDecrement: (CartModel) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 10.sdp) // Horizontal padding for the list
            .fillMaxSize() // Fill available size
            .padding(top = 5.sdp) // Top padding for the list
    ) {
        items(productList) { product ->
            CartProductInfo(
                product = product,
                onDecrement = onDecrement,
                onIncrement = onIncrement
            )
        }
    }
}

@Composable
@Preview
fun CartListPreview() {
    CartList(modifier = Modifier, productList = listOf())
}
