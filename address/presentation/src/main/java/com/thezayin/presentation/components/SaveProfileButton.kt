package com.thezayin.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable button to save the profile information.
 *
 * This button is styled with rounded corners and a black background, and triggers
 * the provided [onClick] action when pressed.
 *
 * @param onClick Lambda function that defines the action when the button is clicked.
 */
@Composable
fun SaveProfileButton(
    onClick: () -> Unit = {} // Default action is an empty lambda if not provided
) {
    Button(
        onClick = { onClick() }, // Trigger the onClick lambda when the button is clicked
        modifier = Modifier
            .padding(bottom = 20.sdp) // Bottom padding to give space between other components
            .fillMaxWidth() // Button fills the entire width of the screen
            .height(40.sdp) // Fixed height for the button
            .padding(horizontal = 20.sdp), // Horizontal padding for the button
        shape = RoundedCornerShape(10.sdp), // Rounded corners for the button with 10dp radius
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = com.thezayin.assets.R.color.black) // Button background color (black)
        )
    ) {
        // Text inside the button
        Text(
            text = "Save", // Button label
            color = colorResource(id = com.thezayin.assets.R.color.white), // Text color (white)
            fontSize = 12.ssp, // Font size
            fontFamily = FontFamily(Font(com.thezayin.assets.R.font.noto_sans_bold)) // Custom bold font
        )
    }
}

/**
 * A preview function to display how the SaveProfileButton looks in isolation.
 * This is used only for development preview in Android Studio.
 */
@Preview
@Composable
fun SaveProfileButtonPreview() {
    SaveProfileButton {}
}