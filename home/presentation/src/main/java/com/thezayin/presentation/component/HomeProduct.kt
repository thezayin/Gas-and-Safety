package com.thezayin.presentation.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import coil.compose.AsyncImage
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable function to display a product item in a list with an image, description,
 * price, and an "Add to Cart" button.
 *
 * @param modifier Modifier to adjust layout behavior and appearance. Defaults to Modifier.
 * @param productId Unique identifier for the product.
 * @param productName Name of the product.
 * @param productPrice Price of the product.
 * @param productDescription A short description of the product.
 * @param productImage Image URI for the product (optional).
 * @param onCartClick Lambda function to handle the "Add to Cart" button click.
 * @param onProductClick Lambda function to handle clicking on the product.
 */
@Composable
fun HomeProduct(
    modifier: Modifier = Modifier,
    productId: String,
    productName: String?,
    productPrice: String?,
    productDescription: String?,
    productImage: Uri? = null,
    onCartClick: () -> Unit = {},
    onProductClick: (String) -> Unit
) {
    // Outer Box that provides padding, background, and rounded corners
    Box(
        modifier = modifier
            .padding(vertical = 5.sdp) // Adds padding between products
            .clip(shape = RoundedCornerShape(10.sdp)) // Clips the shape to rounded corners
            .background(color = colorResource(id = R.color.semi_transparent)) // Semi-transparent background
            .fillMaxWidth() // Makes the product box take up the full width of the parent
    ) {
        // Column to stack content vertically
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.sdp, max = 400.sdp) // Restrict height between min and max
                .padding(horizontal = 15.sdp) // Padding inside the column
                .clickable { onProductClick(productId) }, // Handle product click event
            verticalArrangement = Arrangement.SpaceBetween, // Space items vertically
            horizontalAlignment = Alignment.End // Align children to the end horizontally
        ) {
            // Row containing the product image and its details
            Row(
                modifier = Modifier
                    .padding(top = 10.sdp)
                    .fillMaxWidth(), // Row fills the width
                verticalAlignment = Alignment.CenterVertically, // Center contents vertically
                horizontalArrangement = Arrangement.Start // Align content to the start
            ) {
                // Product image
                AsyncImage(
                    model = productImage ?: R.drawable.ic_mail, // Use provided image or fallback
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(80.sdp)) // Image with circular clipping
                        .size(50.sdp) // Image size
                        .background(color = Color.White), // Background behind the image
                    contentScale = ContentScale.Fit // Fit image within its bounds
                )

                // Column for product name and description
                Column(
                    modifier = Modifier.padding(start = 10.sdp)
                ) {
                    Text(
                        text = productName ?: "Product Name", // Fallback if no product name
                        color = colorResource(id = R.color.black), // Text color
                        fontSize = 10.ssp, // Scalable font size
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)) // Bold font family
                    )
                    Spacer(modifier = Modifier.height(2.sdp)) // Space between text elements
                    Text(
                        text = productDescription
                            ?: "Composite Fiber LPG Cylinder of BGC (Burhan Gas Company) Carries 10 Kg Gas", // Fallback description
                        color = colorResource(id = R.color.black), // Text color
                        fontSize = 8.ssp, // Scalable font size
                        fontFamily = FontFamily(Font(R.font.noto_sans_regular)) // Regular font family
                    )
                }
            }

            // Row for the price and "Add to Cart" button
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Row fills the width
                    .padding(top = 5.sdp), // Padding above the row
                verticalAlignment = Alignment.CenterVertically, // Align items vertically in the center
                horizontalArrangement = Arrangement.SpaceBetween // Space items evenly in the row
            ) {
                // Product price text
                Text(
                    modifier = Modifier.padding(start = 5.sdp),
                    text = " Rs: ${productPrice ?: "1000"}", // Fallback price if not provided
                    color = colorResource(id = R.color.black), // Text color
                    fontSize = 12.ssp, // Scalable font size
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)) // Bold font family
                )

                // "Add to Cart" button
                Card(
                    modifier = Modifier
                        .padding(bottom = 10.sdp) // Padding below the button
                        .width(70.sdp) // Set button width
                        .height(30.sdp), // Set button height
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.black) // Background color of the button
                    ),
                    shape = RoundedCornerShape(5.sdp) // Rounded corners for the button
                ) {
                    // Row inside the button for text and icon
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 5.sdp)
                            .fillMaxSize() // Row fills the button's size
                            .clickable { onCartClick() }, // Handle cart button click
                        verticalAlignment = Alignment.CenterVertically, // Center content vertically
                        horizontalArrangement = Arrangement.Center // Center content horizontally
                    ) {
                        Text(
                            text = "Add to Cart",
                            color = colorResource(id = R.color.white), // White text color
                            fontSize = 8.ssp // Scalable font size
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_cart), // Cart icon
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = colorResource(id = R.color.white)), // Tint the icon white
                            modifier = Modifier.size(10.sdp) // Icon size
                        )
                    }
                }
            }
        }
    }
}