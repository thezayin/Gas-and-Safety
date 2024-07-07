package com.thezayin.lpg.presentation.admin.products.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.lpg.presentation.admin.products.presentation.AdminProductViewModel

@Composable
fun AdminProductList(
    modifier: Modifier,
    viewModel: AdminProductViewModel,
    navigator: DestinationsNavigator
) {
    val productList = viewModel.getAdminProducts.collectAsState().value.list
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .padding(top = 10.dp),
    ) {
        items(productList.size) { product ->
            ProductList(
                modifier = modifier,
                navigator = navigator,
                product = productList[product]
            )
        }
    }
}