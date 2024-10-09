package com.thezayin.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.domain.model.HomeProdModel
import ir.kaaveh.sdpcompose.sdp

/**
 * A composable function that displays a list of products using LazyColumn.
 *
 * @param modifier Modifier to adjust layout behavior and appearance.
 * @param productList List of products to be displayed. It can be null.
 * @param onCartClick Lambda function to handle the "Add to Cart" button click for a product.
 * @param onProductClick Lambda function to handle clicking on a product for navigation or details.
 */
@Composable
fun HomeProductList(
    modifier: Modifier = Modifier,
    productList: List<HomeProdModel>?,
    onCartClick: (HomeProdModel) -> Unit,
    onProductClick: (String) -> Unit,
) {
    // LazyColumn to display the list of products efficiently
    LazyColumn(
        modifier = modifier
            .padding(top = 20.sdp) // Padding at the top for spacing
            .padding(horizontal = 10.sdp) // Padding on the sides
            .fillMaxSize() // Make the LazyColumn fill the available size
    ) {
        // Check if the product list is not null before displaying items
        productList?.let { list ->
            // For each product in the list, create a HomeProduct composable
            items(list.size) { index ->
                val product = list[index]
                HomeProduct(
                    modifier = Modifier, // Pass the Modifier
                    productId = product.id!!, // Product ID, assuming it's non-nullable
                    productName = product.name, // Product name
                    productPrice = product.price, // Product price
                    productDescription = product.description, // Product description
                    productImage = product.imageUri, // Product image URI
                    onCartClick = { onCartClick(product) }, // Pass product to cart click callback
                    onProductClick = onProductClick // Pass product ID to product click callback
                )
            }
        }
    }
}