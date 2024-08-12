package com.thezayin.userhome.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.HomeProductsModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun HomeProductList(
    modifier: Modifier,
    productList: List<HomeProductsModel>,
    onClick: (HomeProductsModel) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(top = 30.sdp)
            .padding(horizontal = 15.sdp)
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