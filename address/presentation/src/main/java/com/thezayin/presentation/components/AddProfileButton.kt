package com.thezayin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable button for adding a new profile.
 *
 * This component displays a rounded button with the text "Add New" and an icon, allowing users to add a new profile.
 * The button is clickable, and triggers the provided [onClick] callback when pressed.
 *
 * @param onClick Lambda function to handle the click action for adding a new profile.
 */
@Composable
fun AddProfileButton(
    onClick: () -> Unit // Callback triggered when the button is clicked
) {
    Row(
        modifier = Modifier
            .padding(top = 25.sdp, bottom = 10.sdp) // Padding from the top and bottom
            .fillMaxWidth(), // Takes up the full width of the screen
        verticalAlignment = Alignment.CenterVertically, // Vertically centers content
        horizontalArrangement = Arrangement.End // Aligns the button to the end of the row (right side)
    ) {
        // Card component with rounded corners and semi-transparent background
        Card(
            modifier = Modifier
                .width(70.sdp) // Defines the width of the card
                .height(30.sdp), // Defines the height of the card
            shape = RoundedCornerShape(30.sdp), // Creates rounded corners with 30dp radius
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent) // Sets the background color of the card
            )
        ) {
            // Row inside the card to hold the text and icon
            Row(
                modifier = Modifier
                    .fillMaxSize() // Fills the entire size of the card
                    .clickable { onClick() }, // Makes the card clickable, triggering the provided onClick action
                horizontalArrangement = Arrangement.Center, // Centers content horizontally within the card
                verticalAlignment = Alignment.CenterVertically // Centers content vertically within the card
            ) {
                // Text for the button
                Text(
                    text = "Add New", // Button label
                    fontSize = 8.ssp, // Font size using scalable sp units
                    color = colorResource(R.color.black), // Text color
                    fontFamily = FontFamily(Font(R.font.abeezee_italic)) // Custom font family
                )
                // Icon next to the text
                Image(
                    painter = painterResource(id = R.drawable.ic_add), // Image resource (add icon)
                    contentDescription = null, // No content description needed for decorative icons
                    modifier = Modifier
                        .padding(start = 5.sdp) // Padding to create space between the text and icon
                        .size(10.sdp) // Icon size
                )
            }
        }
    }
}

/**
 * Preview function to display how the AddProfileButton looks in isolation.
 */
@Preview
@Composable
fun AddProfileButtonPreview() {
    AddProfileButton { /* No action needed for preview */ }
}