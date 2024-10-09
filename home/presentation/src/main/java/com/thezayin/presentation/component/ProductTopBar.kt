package com.thezayin.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable function that represents a top bar with a back button, screen title, and a contact button.
 *
 * @param modifier Modifier to adjust layout behavior and appearance.
 * @param screen The title of the screen to be displayed in the center of the top bar.
 * @param onBackClick Lambda function to handle back button clicks.
 * @param onContactClick Lambda function to handle contact button clicks.
 */
@Composable
fun ProductTopBar(
    modifier: Modifier = Modifier,
    screen: String,
    onBackClick: () -> Unit,
    onContactClick: () -> Unit,
) {
    // Row layout to arrange the back button, screen title, and contact button horizontally
    Row(
        modifier = modifier
            .fillMaxWidth() // Make the row fill the width of the screen
            .padding(horizontal = 15.sdp).padding(top = 20.sdp), // Apply top and horizontal padding
        horizontalArrangement = Arrangement.SpaceBetween, // Space elements evenly across the row
        verticalAlignment = Alignment.CenterVertically // Vertically center all elements
    ) {
        // Back button image
        Image(
            painter = painterResource(id = R.drawable.ic_back), // Back button icon
            contentDescription = null, // Content description for accessibility can be added if needed
            modifier = Modifier
                .clickable { onBackClick() } // Handle back button clicks
                .size(20.sdp) // Size of the back button icon
        )

        // Screen title text in the center
        Text(
            text = screen, // The screen title passed as a parameter
            color = colorResource(id = R.color.black), // Text color
            fontSize = 12.ssp, // Scalable font size
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)), // Bold font family for emphasis
        )

        // Contact button image
        Image(
            painter = painterResource(id = R.drawable.ic_call), // Contact button icon
            contentDescription = null, // Content description for accessibility can be added if needed
            modifier = Modifier
                .size(20.sdp) // Size of the contact button icon
                .clickable { onContactClick() } // Handle contact button clicks
        )
    }
}
