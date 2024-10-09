package com.thezayin.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable function to display a Buy Button with a price and an icon.
 *
 * @param modifier Modifier to adjust layout behavior and appearance. Defaults to Modifier.
 * @param price The price to display on the button. Default is "1000".
 * @param onBuyClick Lambda function invoked when the Buy button is clicked.
 */
@Preview
@Composable
fun BuyButton(
    modifier: Modifier = Modifier,
    price: String = "1000",
    onBuyClick: () -> Unit = {}
) {
    // A Row that contains the price and a button for buying
    Row(
        modifier = modifier
            .fillMaxWidth() // Ensures the Row fills the entire width of its parent
            .padding(horizontal = 15.sdp, vertical = 20.sdp), // Adds padding around the Row
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically, // Vertically centers its content
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(40.sdp) // Adds space between elements
    ) {
        // Display the price text
        Text(
            text = "$price RS", // Format the price with a currency symbol
            fontSize = 22.ssp, // Use scalable sp unit for text size
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)), // Set a custom font
            color = colorResource(R.color.black) // Set text color
        )

        // Buy button that triggers the provided onBuyClick lambda when clicked
        Button(
            onClick = { onBuyClick() },
            modifier = Modifier
                .padding(start = 10.sdp) // Add padding to the start of the button
                .fillMaxWidth() // Make the button fill the available width
                .height(50.sdp), // Set the button height
            shape = RoundedCornerShape(20.sdp), // Apply rounded corners to the button
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.black) // Set the background color of the button
            )
        ) {
            // Row inside the button to align text and icon horizontally
            Row(
                modifier = Modifier.fillMaxSize(), // Make the Row fill the button size
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically, // Center items vertically
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly // Distribute space evenly between items
            ) {
                // Text for the Buy label on the button
                Text(
                    text = "Buy",
                    fontSize = 16.ssp, // Use scalable sp unit for text size
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)), // Set a custom font
                    color = colorResource(R.color.white) // Set text color to white
                )
                // Cart icon image inside the button
                Image(
                    modifier = Modifier.size(25.sdp), // Set the size of the image
                    painter = painterResource(id = R.drawable.ic_cart), // Load the cart icon drawable
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.white)), // Tint the icon to match the text color
                    contentDescription = "cart" // Provide a content description for accessibility
                )
            }
        }
    }
}