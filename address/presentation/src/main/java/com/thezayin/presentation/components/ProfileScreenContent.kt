package com.thezayin.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import com.thezayin.assets.R
import com.thezayin.common.component.UserTopBar
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.FieldsDialog
import com.thezayin.common.dialogs.LoadingDialog

/**
 * Composable function that renders the profile screen for adding or editing a profile.
 *
 * @param isError Indicates if an error occurred during profile operations.
 * @param isLoading Indicates if a loading process is in progress.
 * @param areas List of areas to choose from, based on the selected city.
 * @param errorMessage The error message to display if there's an issue.
 * @param cities List of available cities for selection.
 * @param city The currently selected city.
 * @param area The currently selected area.
 * @param isAddedSuccessful Flag to indicate if the profile addition was successful.
 * @param selectedAreaIndex The index of the currently selected area.
 * @param checkField State to track if required fields are missing.
 * @param name The state that holds the user's name.
 * @param expandedCity State to control the expansion of the city dropdown.
 * @param expandedArea State to control the expansion of the area dropdown.
 * @param selectedCityIndex The index of the currently selected city.
 * @param address The state that holds the user's address.
 * @param phoneNumber The state that holds the user's phone number.
 * @param hideError Function to hide the error dialog.
 * @param navigateBack Function to handle navigation back to the previous screen.
 * @param navigateToAddress Function to navigate to the address listing screen.
 * @param navigateToContactUs Function to navigate to the contact us screen.
 * @param fetchAreaList Function to fetch the list of areas based on the selected city.
 * @param addProfile Function to add the new profile to the data store.
 */
@Composable
fun ProfileScreenContent(
    isError: Boolean, // Error state
    isLoading: Boolean, // Loading state
    areas: List<String>, // List of areas for selection
    errorMessage: String, // Error message to display
    cities: List<String>, // List of cities for selection
    city: MutableState<String>, // State of the selected city
    area: MutableState<String>, // State of the selected area
    isAddedSuccessful: Boolean, // Whether the profile addition was successful
    selectedAreaIndex: MutableIntState, // Index of the selected area
    checkField: MutableState<Boolean>, // State to check if fields are empty
    name: MutableState<TextFieldValue>, // State for user's name
    expandedCity: MutableState<Boolean>, // State for the city dropdown expansion
    expandedArea: MutableState<Boolean>, // State for the area dropdown expansion
    selectedCityIndex: MutableState<Int>, // Index of the selected city
    address: MutableState<TextFieldValue>, // State for user's address
    phoneNumber: MutableState<TextFieldValue>, // State for user's phone number
    hideError: () -> Unit, // Function to hide error dialog
    navigateBack: () -> Unit, // Function to navigate back to previous screen
    navigateToAddress: () -> Unit, // Function to navigate to address list
    navigateToContactUs: () -> Unit, // Function to navigate to contact us screen
    fetchAreaList: (String) -> Unit, // Function to fetch areas based on the selected city
    addProfile: (String, String, String, String, String) -> Unit // Function to add a new profile
) {

    // Check if required fields are missing and show dialog if necessary
    if (checkField.value) {
        FieldsDialog(showDialog = { checkField.value = false })
    }

    // Show loading dialog if loading is in progress
    if (isLoading) {
        LoadingDialog()
    }

    // Show error dialog if an error occurred
    if (isError) {
        ErrorQueryDialog(
            showDialog = { hideError() },
            callback = { },
            error = errorMessage
        )
    }

    // Main Scaffold layout of the Profile Screen
    Scaffold(
        modifier = Modifier
            .statusBarsPadding() // Adds padding to avoid overlapping with the status bar
            .navigationBarsPadding(), // Adds padding to avoid overlapping with the navigation bar
        containerColor = colorResource(id = R.color.semi_transparent), // Background color for the container
        topBar = {
            // Custom TopBar with back navigation and contact us button
            UserTopBar(
                modifier = Modifier,
                screen = "Profile", // Title of the screen
                onBackClick = { navigateBack() }, // Navigate back when clicked
                onContactClick = { navigateToContactUs() } // Navigate to Contact Us screen
            )
        },
        bottomBar = {
            // Save button for adding the profile
            SaveProfileButton {
                // Check if any required field is empty
                if (name.value.text.isEmpty() || phoneNumber.value.text.isEmpty() || city.value.isEmpty() || area.value.isEmpty() || address.value.text.isEmpty()) {
                    checkField.value = true // Show missing fields dialog if any field is empty
                } else {
                    // Add the profile if all required fields are filled
                    addProfile(
                        name.value.text,
                        phoneNumber.value.text,
                        city.value,
                        area.value,
                        address.value.text
                    )
                    navigateToAddress() // Navigate to address listing after successful addition
                }
            }
        }) { padding ->
        // Main content of the Profile screen
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()) // Enable scrolling
                .padding(padding) // Apply padding from the Scaffold
                .fillMaxWidth() // Fill the width of the parent container
                .fillMaxSize(), // Fill the height of the parent container
            verticalArrangement = Arrangement.SpaceBetween // Space elements evenly within the column
        ) {
            Column(modifier = Modifier) {
                // User input fields for name and phone number
                UserDetails(
                    name = name,
                    phoneNumber = phoneNumber,
                )
                // Location input fields for city and area selection
                LocationDetails(
                    modifier = Modifier,
                    cityList = cities,
                    areaList = areas,
                    address = address,
                    selectedCity = city,
                    selectedArea = area,
                    selectedCityIndex = selectedCityIndex,
                    selectedAreaIndex = selectedAreaIndex,
                    expandedCity = expandedCity,
                    expandedArea = expandedArea,
                    callBack = {
                        fetchAreaList(it) // Fetch areas when a city is selected
                    },
                )
            }
        }
    }
}