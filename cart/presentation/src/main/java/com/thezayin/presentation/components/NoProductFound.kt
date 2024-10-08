package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable function that displays a message when there are no products in the cart.
 *
 * This function presents a card with a message indicating that the cart is empty.
 */
@Composable
fun NoProductFound() {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.sdp) // Horizontal padding for spacing
            .padding(top = 100.sdp) // Top padding to position the card lower on the screen
            .fillMaxWidth() // Fill the available width
            .height(150.sdp), // Set a fixed height for the card
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.semi_transparent) // Background color of the card
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(), // Fill the size of the card
            horizontalArrangement = Arrangement.Center, // Center content horizontally
            verticalAlignment = Alignment.CenterVertically // Center content vertically
        ) {
            Text(
                text = "No Products in Cart", // Message to display
                fontSize = 15.ssp, // Font size for the text
                fontFamily = FontFamily(Font(R.font.noto_sans_bold)), // Font style
                color = colorResource(id = R.color.black) // Text color
            )
        }
    }
}

@Composable
@Preview
fun NoProductFoundPreview() {
    NoProductFound() // Preview the NoProductFound component
}