package com.thezayin.presentation.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp

/**
 * A composable function to display a product image in a card with placeholder images for loading.
 *
 * @param modifier Modifier to adjust layout behavior and appearance.
 * @param productImage URI of the product image to be displayed (optional).
 */
@Composable
fun ProductImage(
    modifier: Modifier = Modifier,
    productImage: Uri? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth(), // Fill the width of the parent
        verticalArrangement = Arrangement.Center, // Center the content vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center the content horizontally
    ) {
        // Card to contain the product image with some padding and rounded corners
        Card(
            modifier = Modifier
                .padding(15.sdp) // Padding around the card
                .fillMaxWidth() // Fill the available width
                .height(200.sdp), // Set height for the card
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.semi_transparent) // Background color for the card
            )
        ) {
            // Row to horizontally align the product image
            Row(
                modifier = Modifier.fillMaxWidth(), // Fill the width of the row
                verticalAlignment = Alignment.CenterVertically, // Vertically center the content
                horizontalArrangement = Arrangement.Center // Center the content horizontally
            ) {
                // Display the product image using AsyncImage
                AsyncImage(
                    model = productImage, // The product image URI
                    modifier = Modifier.fillMaxSize(), // Fill the available size
                    placeholder = painterResource(R.drawable.ic_connection), // Placeholder image while loading
                    contentDescription = "Product Image", // Content description for accessibility
                    contentScale = ContentScale.Fit // Scale the image to fit within the container
                )
            }
        }

        // Row for placeholder spacers under the image
        Row(
            verticalAlignment = Alignment.CenterVertically, // Vertically center the spacers
            horizontalArrangement = Arrangement.spacedBy(5.sdp) // Add space between the spacers
        ) {
            // Repeating Spacer elements with background and rounded corners
            repeat(3) {
                Spacer(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(5.sdp)) // Rounded corners for the spacer
                        .background(color = colorResource(R.color.semi_transparent)) // Background color
                        .width(20.sdp) // Set width for the spacer
                        .height(30.sdp) // Set height for the spacer
                )
            }
        }
    }
}

/**
 * Preview function for ProductImage composable.
 */
@Preview
@Composable
fun ProductImagePreview() {
    ProductImage()
}