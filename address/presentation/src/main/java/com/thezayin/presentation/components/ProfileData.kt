package com.thezayin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.thezayin.databases.model.ProfileModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable to display a user's profile information inside a card.
 * This card includes the user's name, address, city, phone number, and a delete option.
 *
 * @param profile The [ProfileModel] containing the user's data to display.
 * @param onDeleteProfile A lambda function to handle the delete action when the delete icon is clicked.
 */
@Composable
fun ProfileCard(
    profile: ProfileModel,
    onDeleteProfile: (Int) -> Unit // Action triggered when the delete icon is clicked, passing the profile ID.
) {
    // A card to display the profile data with padding and semi-transparent background
    Card(
        modifier = Modifier
            .padding(top = 10.sdp) // Padding at the top
            .fillMaxWidth() // Card takes up the full width of the screen
            .heightIn(max = 400.sdp), // Restrict maximum height to 400 sdp
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.semi_transparent) // Card background color
        )
    ) {
        // Column layout to organize profile information vertically
        Column(
            modifier = Modifier
                .padding(horizontal = 10.sdp) // Horizontal padding for inner content
                .fillMaxWidth() // Fill the entire width of the card
                .heightIn(max = 400.sdp), // Ensure a max height for the card content
            horizontalAlignment = Alignment.Start, // Align content to the start
            verticalArrangement = Arrangement.Top // Align content to the top
        ) {
            // Row layout to place the delete icon at the top-right corner of the card
            Row(
                modifier = Modifier.fillMaxWidth(), // Fill the width of the row
                verticalAlignment = Alignment.CenterVertically, // Vertically align contents to the center
                horizontalArrangement = Arrangement.End // Place content at the end (right side)
            ) {
                // Delete icon, which is clickable to trigger the delete action
                Image(
                    painter = painterResource(id = R.drawable.ic_delete), // Delete icon resource
                    contentDescription = "Delete Profile", // Accessibility description
                    modifier = Modifier
                        .clickable { onDeleteProfile(profile.id ?: 0) } // Trigger delete action with the profile ID
                        .padding(top = 10.sdp) // Add top padding for better spacing
                        .size(18.sdp) // Set the size of the delete icon
                )
            }

            // Address text displaying the user's full address
            Text(
                text = profile.address ?: "No Address Provided",
                modifier = Modifier.padding(top = 5.sdp),
                fontSize = 10.ssp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_bold))
            )

            // Area and city text displaying the user's area and city
            Text(
                text = "${profile.area ?: "No Area"}, ${profile.city ?: "No City"}",
                modifier = Modifier.padding(top = 5.sdp),
                fontSize = 10.ssp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_bold))
            )

            // Name text displaying the user's name
            Text(
                text = profile.name ?: "No Name Provided",
                modifier = Modifier.padding(top = 5.sdp),
                fontSize = 10.ssp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )

            // Phone number text displaying the user's phone number
            Text(
                text = profile.phoneNumber ?: "No Phone Number Provided",
                modifier = Modifier.padding(top = 5.sdp, bottom = 15.sdp), // Add bottom padding for spacing
                fontSize = 10.ssp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )
        }
    }
}

/**
 * A preview function to showcase how the ProfileCard composable looks.
 * This function is used only for development preview in Android Studio.
 */
@Composable
@Preview
fun ProfileCardPreview() {
    ProfileCard(
        profile = ProfileModel(
            name = "John Doe",
            phoneNumber = "123-456-7890",
            email = "john.doe@example.com",
            address = "123 Main St",
            area = "Downtown",
            city = "Metropolis"
        ),
        onDeleteProfile = {} // No action for the preview
    )
}