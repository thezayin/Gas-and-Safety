package com.thezayin.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ir.kaaveh.sdpcompose.sdp

/**
 * A composable function for the top bar that allows navigation to settings and contact.
 *
 * @param modifier Modifier to adjust layout behavior and appearance.
 * @param navigateToSetting Lambda function to handle the click event for navigating to the settings screen.
 * @param navigateToContact Lambda function to handle the click event for navigating to the contact screen.
 */
@Composable
fun TopBarComponent(
    modifier: Modifier = Modifier,
    navigateToSetting: () -> Unit = {},
    navigateToContact: () -> Unit = {},
) {
    // Row layout to arrange settings and contact icons horizontally
    Row(
        modifier = modifier
            .fillMaxWidth() // Fill the width of the parent
            .padding(top = 20.sdp) // Padding from the top
            .padding(horizontal = 15.sdp), // Horizontal padding
        horizontalArrangement = Arrangement.SpaceBetween, // Space icons on opposite ends
        verticalAlignment = Alignment.CenterVertically // Center align icons vertically
    ) {
        // Settings icon (menu) with clickable action
        Image(
            painter = painterResource(id = com.thezayin.assets.R.drawable.ic_menue),
            contentDescription = null, // Content description can be added for accessibility
            modifier = Modifier
                .clickable { navigateToSetting() } // Click to navigate to settings
                .size(15.sdp), // Set the size of the image
        )

        // Contact icon (call) with clickable action
        Image(
            painter = painterResource(id = com.thezayin.assets.R.drawable.ic_call),
            contentDescription = null, // Content description can be added for accessibility
            modifier = Modifier
                .size(20.sdp) // Set the size of the image
                .clickable { navigateToContact() } // Click to navigate to contact
        )
    }
}

/**
 * Preview function for TopBarComponent composable.
 */
@Composable
@Preview
fun TopBarComponentPreview() {
    TopBarComponent(modifier = Modifier)
}