package com.thezayin.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable function that displays a customizable bottom bar for the Home screen.
 *
 * @param showBadge Boolean flag to indicate whether to show a badge on the cart icon.
 * @param modifier Modifier to adjust layout behavior and appearance. Defaults to Modifier.
 * @param badgeText The text to display inside the badge (if shown).
 * @param navigateToHistory Lambda function to navigate to the history screen when the history icon is clicked.
 * @param navigateToCart Lambda function to navigate to the cart screen when the cart icon is clicked.
 * @param navigateToProfile Lambda function to navigate to the profile screen when the profile icon is clicked.
 */
@Composable
fun HomeBottomBar(
    showBadge: Boolean = false,
    modifier: Modifier = Modifier,
    badgeText: String = "",
    navigateToHistory: () -> Unit = {},
    navigateToCart: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
) {
    // Row to align the items horizontally
    Row(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(topEnd = 15.sdp, topStart = 15.sdp) // Rounded corners for the top of the bottom bar
            )
            .background(color = colorResource(id = R.color.semi_transparent)) // Semi-transparent background
            .padding(bottom = 10.sdp, top = 10.sdp) // Padding for vertical alignment
            .fillMaxWidth() // Make the row fill the entire width
            .height(35.sdp) // Set height for the bottom bar
            .padding(horizontal = 30.sdp), // Horizontal padding between items
        verticalAlignment = Alignment.CenterVertically, // Vertically center items
        horizontalArrangement = Arrangement.SpaceBetween // Space items evenly across the row
    ) {
        // Home button with icon and text
        Card(
            modifier = Modifier
                .width(60.sdp) // Set width for the Home button
                .height(30.sdp), // Set height for the Home button
            shape = RoundedCornerShape(25.sdp), // Rounded shape for the Home button
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.black) // Background color for the Home button
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Fill the button's width
                    .fillMaxHeight(), // Fill the button's height
                verticalAlignment = Alignment.CenterVertically, // Vertically center items inside the button
                horizontalArrangement = Arrangement.Center // Center items horizontally inside the button
            ) {
                Image(
                    modifier = Modifier.size(12.sdp), // Set size for the Home icon
                    painter = painterResource(id = R.drawable.ic_home), // Use the home icon drawable
                    contentDescription = null, // Content description for accessibility
                )
                Text(
                    text = "Home",
                    fontSize = 8.ssp, // Set font size for the Home label
                    color = colorResource(id = R.color.white), // Set text color to white
                    modifier = Modifier.padding(start = 2.sdp) // Padding to the left of the text
                )
            }
        }

        // History icon, clickable to navigate to history
        Image(
            painter = painterResource(id = R.drawable.ic_history), // Use the history icon drawable
            contentDescription = null, // Content description for accessibility
            modifier = Modifier
                .size(17.sdp) // Set size for the history icon
                .clickable {
                    navigateToHistory() // Navigate to history when clicked
                }
        )

        // Cart icon with optional badge, clickable to navigate to cart
        BadgedBox(
            badge = {
                if (showBadge) {
                    Badge { Text(text = badgeText) } // Show badge if `showBadge` is true
                }
            },
            modifier = Modifier
                .size(25.sdp) // Set size for the cart icon
                .clickable {
                    navigateToCart() // Navigate to cart when clicked
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cart), // Use the cart icon drawable
                contentDescription = null, // Content description for accessibility
                modifier = Modifier.size(18.sdp) // Set size for the icon inside the badge box
            )
        }

        // Profile icon, clickable to navigate to profile
        Image(
            painter = painterResource(id = R.drawable.ic_profile), // Use the profile icon drawable
            contentDescription = null, // Content description for accessibility
            modifier = Modifier
                .size(18.sdp) // Set size for the profile icon
                .clickable {
                    navigateToProfile() // Navigate to profile when clicked
                }
        )
    }
}

/**
 * Preview function for HomeBottomBar composable with default parameters.
 */
@Preview
@Composable
fun HomeBottomBarPreview() {
    HomeBottomBar(showBadge = true, badgeText = "10")
}