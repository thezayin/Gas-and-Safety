package com.thezayin.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.databases.model.CartModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun CartList(
    modifier: Modifier,
    productList: List<CartModel>,
    onIncrement: (CartModel) -> Unit = {},
    onDecrement: (CartModel) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 10.sdp)
            .fillMaxSize()
            .padding(top = 5.sdp),
    ) {
        items(productList.size) { product ->
            CartProductInfo(
                product = productList[product],
                onDecrement = onDecrement,
                onIncrement = onIncrement
            )
        }
    }
}

@Composable
@Preview
fun CartListPreview() {
    CartList(Modifier, productList = listOf())
}