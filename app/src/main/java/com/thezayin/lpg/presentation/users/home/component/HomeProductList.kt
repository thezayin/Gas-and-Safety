package com.thezayin.lpg.presentation.users.home.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.HomeProductsModel

@Composable
fun HomeProductList(
    modifier: Modifier,
    productList: List<com.thezayin.entities.HomeProductsModel>,
    onClick: (com.thezayin.entities.HomeProductsModel) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(top = 30.dp)
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
    ) {
        items(productList.size) { product ->
            HomeProduct(
                product = productList[product],
                onClick = {
                    onClick(productList[product])
                }
            )
        }
    }
}