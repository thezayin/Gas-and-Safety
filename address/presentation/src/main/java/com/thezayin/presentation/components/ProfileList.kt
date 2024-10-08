package com.thezayin.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.databases.model.ProfileModel

/**
 * A composable that displays a list of profiles in a scrollable column.
 *
 * @param profiles A list of [ProfileModel] representing the profiles to display.
 * @param onDeleteProfile A lambda function to handle the delete action when a profile is deleted.
 */
@Composable
fun ProfileList(
    profiles: List<ProfileModel>?, // List of profiles to display
    onDeleteProfile: (Int) -> Unit // Action to trigger when a profile's delete button is clicked
) {
    // LazyColumn for efficiently displaying a scrollable list of profiles
    LazyColumn(
        modifier = Modifier.fillMaxSize() // Fill the entire available space
    ) {
        // Check if the profile list is not null before rendering items
        profiles?.let {
            items(it.size) { index ->
                // Render each profile as a card
                ProfileCard(
                    profile = it[index], // Pass the current profile to the card
                    onDeleteProfile = onDeleteProfile // Pass the delete action to the card
                )
            }
        }
    }
}