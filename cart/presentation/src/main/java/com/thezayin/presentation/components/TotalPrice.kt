package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable function that displays the total price in the cart.
 *
 * This function presents the total price formatted as "Total Price: $price".
 *
 * @param price The total price to be displayed.
 */
@Composable
fun TotalPrice(price: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.sdp) // Horizontal padding for spacing
            .fillMaxWidth(), // Fill the available width
        verticalAlignment = Alignment.CenterVertically, // Center the text vertically
        horizontalArrangement = Arrangement.Center // Center the text horizontally
    ) {
        Text(
            text = "Total Price: $price", // Display the total price
            modifier = Modifier.padding(top = 15.sdp, bottom = 15.sdp), // Padding around the text
            fontSize = 18.ssp, // Font size for the text
            fontWeight = FontWeight.ExtraBold, // Boldness of the font
            fontFamily = FontFamily(Font(com.thezayin.assets.R.font.noto_sans_bold)) // Font style
        )
    }
}

@Composable
@Preview
fun TotalPricePreview() {
    TotalPrice(price = "1000") // Preview the TotalPrice component with a sample price
}