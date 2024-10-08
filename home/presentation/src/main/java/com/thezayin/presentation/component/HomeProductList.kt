package com.thezayin.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.domain.model.HomeProdModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun HomeProductList(
    modifier: Modifier,
    productList: List<HomeProdModel>?,
    onCartClick: (HomeProdModel) -> Unit,
    onProductClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(top = 20.sdp).padding(horizontal = 10.sdp).fillMaxSize(),
    ) {
        if (productList != null) {
            items(productList.size) { product ->
                HomeProduct(
                    modifier = Modifier,
                    productId = productList[product].id!!,
                    productName = productList[product].name,
                    productPrice = productList[product].price,
                    productDescription = productList[product].description,
                    productImage = productList[product].imageUri,
                    onCartClick = { onCartClick(productList[product]) },
                    onProductClick = onProductClick
                )
            }
        }
    }
}