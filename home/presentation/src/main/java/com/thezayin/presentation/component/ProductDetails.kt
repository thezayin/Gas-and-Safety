package com.thezayin.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable function that displays product details, including the product name and description.
 *
 * @param modifier Modifier to adjust layout behavior and appearance.
 * @param productName The name of the product to be displayed.
 * @param productDescription The description of the product to be displayed.
 */
@Composable
fun ProductDetails(
    modifier: Modifier = Modifier,
    productName: String,
    productDescription: String
) {
    // Column layout to stack product name and description vertically
    Column(
        modifier = modifier
            .fillMaxSize() // Fill the available size
            .padding(15.sdp), // Padding around the column
    ) {
        // Row to center the product name horizontally
        Row(
            modifier = Modifier.fillMaxWidth(), // Make the row fill the available width
            verticalAlignment = Alignment.CenterVertically, // Center the text vertically
            horizontalArrangement = Arrangement.Center // Center the text horizontally
        ) {
            Text(
                text = productName, // Display the product name
                textAlign = TextAlign.Center, // Center align the text
                fontSize = 20.ssp, // Scalable font size for the product name
                fontFamily = FontFamily(Font(R.font.noto_sans_bold)), // Bold font for emphasis
                color = colorResource(R.color.black) // Set text color to black
            )
        }

        Spacer(modifier = Modifier.padding(5.sdp)) // Spacer to add space between elements

        // Text displaying the product description
        Text(
            text = productDescription, // Display the product description
            textAlign = TextAlign.Start, // Left-align the text
            fontSize = 12.ssp, // Scalable font size for the description
            fontFamily = FontFamily(Font(R.font.noto_sans_regular)), // Regular font for body text
            color = colorResource(R.color.black) // Set text color to black
        )
    }
}
