package com.thezayin.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.thezayin.presentation.components.AddressScreenContent
import org.koin.compose.koinInject

/**
 * Composable function for displaying the Address Screen.
 *
 * This screen displays a list of saved user addresses, and provides options for navigation,
 * profile management, and error handling.
 *
 * @param navigateBack Callback function to navigate back to the previous screen.
 * @param navigateToContactUs Callback function to navigate to the Contact Us screen.
 * @param navigateToProfile Callback function to navigate to the Profile creation screen.
 */
@Composable
fun AddressScreen(
    navigateBack: () -> Unit, // Function to navigate back to the previous screen
    navigateToContactUs: () -> Unit, // Function to navigate to the Contact Us screen
    navigateToProfile: () -> Unit // Function to navigate to the Profile creation screen
) {
    // Injecting the ProfileViewModel using Koin
    val viewModel: ProfileViewModel = koinInject()

    // Collecting the UI state from the ViewModel as a State
    val state = viewModel.addressUiState.collectAsState().value

    BackHandler(onBack = navigateBack) // Handle back button press

    // Destructuring the state for readability and convenience
    val isLoading = state.isLoading
    val isError = state.isError
    val errorMessage = state.errorMessage
    val profileList = state.getAddresses

    // Rendering the AddressScreenContent with the current UI state and event handlers
    AddressScreenContent(
        isError = isError, // Display error UI if an error has occurred
        isLoading = isLoading, // Display loading UI if loading is in progress
        errorMessage = errorMessage, // Error message to display in case of an error
        list = profileList, // List of profiles to display
        hideError = { viewModel.showError(false) }, // Function to hide error when dismissed
        navigateToContactUs = navigateToContactUs, // Navigation to the Contact Us screen
        navigateBack = navigateBack, // Navigation back to the previous screen
        navigateToProfile = navigateToProfile, // Navigation to Profile creation screen
        deleteProfile = { profileId -> // Function to delete a profile by its ID
            viewModel.deleteProfile(profileId)
        }
    )
}