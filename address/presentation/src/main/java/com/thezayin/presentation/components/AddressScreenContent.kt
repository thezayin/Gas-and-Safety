package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.thezayin.assets.R
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.common.component.UserTopBar
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.databases.model.ProfileModel

/**
 * Composable function for displaying the address screen content.
 *
 * This screen shows a list of saved user addresses, provides options to add a new address,
 * and allows the deletion of existing addresses.
 *
 * @param isLoading Boolean flag to indicate if the screen is currently loading data.
 * @param isError Boolean flag to indicate if there is an error in data fetching.
 * @param errorMessage The error message to display when there is a problem loading data.
 * @param list List of [ProfileModel] objects representing the user addresses.
 * @param hideError Callback function to hide the error dialog.
 * @param navigateBack Callback function to handle the back navigation.
 * @param navigateToContactUs Callback function to navigate to the contact us screen.
 * @param deleteProfile Callback function to delete a specific profile using its ID.
 * @param navigateToProfile Callback function to navigate to the profile creation screen.
 */
@Composable
fun AddressScreenContent(
    isLoading: Boolean, // Loading state
    isError: Boolean, // Error state
    errorMessage: String, // Error message to display
    list: List<ProfileModel>?, // List of user addresses (ProfileModel)
    hideError: () -> Unit, // Function to hide the error dialog
    navigateBack: () -> Unit, // Function to handle back navigation
    navigateToContactUs: () -> Unit, // Function to navigate to contact us
    deleteProfile: (Int) -> Unit, // Function to delete a profile by its ID
    navigateToProfile: () -> Unit, // Function to navigate to profile creation
) {

    // Custom glass effect component applied to the background
    GlassComponent()

    // Sets the status bar and navigation bar colors
    SetBarColors()

    // Shows a loading dialog if the screen is loading
    if (isLoading) {
        LoadingDialog()
    }

    // Shows an error dialog if an error occurs
    if (isError) {
        ErrorQueryDialog(
            showDialog = { hideError() }, // Hides the error when dialog is dismissed
            callback = { }, // Optional callback (unused)
            error = errorMessage // Displays the error message
        )
    }

    // Main scaffold layout for the address screen
    Scaffold(
        modifier = Modifier
            .statusBarsPadding() // Adds padding to avoid status bar overlap
            .navigationBarsPadding(), // Adds padding to avoid navigation bar overlap
        containerColor = colorResource(id = R.color.semi_transparent), // Background color
        topBar = {
            // Top bar with a title and navigation options
            UserTopBar(
                modifier = Modifier, // Default modifier
                screen = "Addresses", // Title of the screen
                onBackClick = { navigateBack() }, // Navigate back when clicked
                onContactClick = { navigateToContactUs() } // Navigate to contact us screen
            )
        },
    ) { padding ->
        // Main column layout for the content of the screen
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp) // Horizontal padding for the content
                .padding(padding) // Inset padding provided by Scaffold for system UI
                .fillMaxWidth() // Fill the full width of the screen
                .fillMaxSize(), // Fill the full height of the screen
            verticalArrangement = Arrangement.SpaceBetween // Space items evenly
        ) {
            // Button to add a new profile
            AddProfileButton { navigateToProfile() } // Navigate to profile creation when clicked

            // List of profiles displayed as a scrollable column
            ProfileList(
                profiles = list,
                onDeleteProfile = deleteProfile
            ) // Delete profile when the delete button is clicked
        }
    }
}