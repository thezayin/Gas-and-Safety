package com.thezayin.usercart.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thezayin.entities.CartModel

@Composable
fun CartList(
    modifier: Modifier,
    productList: List<CartModel>,
    onIncrement: (CartModel) -> Unit = {},
    onDecrement: (CartModel) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .padding(top = 10.dp),
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