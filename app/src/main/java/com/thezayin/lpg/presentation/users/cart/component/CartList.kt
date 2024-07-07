package com.thezayin.lpg.presentation.users.cart.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thezayin.lpg.presentation.users.cart.CartViewModel

@Composable
fun CartList(
    modifier: Modifier,
    cartViewModel: CartViewModel?,
    productList: List<com.thezayin.entities.CartModel>
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
                cartViewModel = cartViewModel
            )
        }
    }
}

@Composable
@Preview
fun CartListPreview() {
    CartList(Modifier, cartViewModel = null, productList = listOf())
}