package com.thezayin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.presentation.components.ProfileScreenContent
import org.koin.compose.koinInject

/**
 * Composable function to display the Profile screen, where users can input profile details
 * like name, phone number, city, and area. It includes navigation, form validation,
 * and profile submission logic.
 *
 * @param navigateBack Function to navigate back to the previous screen.
 * @param navigateToAddress Function to navigate to the address list screen.
 * @param navigateToContactUs Function to navigate to the contact us screen.
 */
@Composable
fun ProfileScreen(
    navigateBack: () -> Unit, // Navigation back handler with a default no-op
    navigateToAddress: () -> Unit, // Navigation to the address list
    navigateToContactUs: () -> Unit // Navigation to contact us page
) {
    // Inject the ProfileViewModel using Koin
    val viewModel: ProfileViewModel = koinInject()

    // Collect the UI state from the ViewModel as a State
    val state = viewModel.addressUiState.collectAsState().value

    // Remember mutable states for various form fields and dropdowns
    val selectedCityIndex = remember { mutableIntStateOf(0) } // State to track selected city index
    val selectedAreaIndex = remember { mutableIntStateOf(0) } // State to track selected area index
    val phoneNumber = remember { mutableStateOf(TextFieldValue()) } // State for the phone number
    val address = remember { mutableStateOf(TextFieldValue()) } // State for the address
    val expandedCity = remember { mutableStateOf(false) } // State for expanding the city dropdown
    val expandedArea = remember { mutableStateOf(false) } // State for expanding the area dropdown
    val checkField = remember { mutableStateOf(false) } // State to check if fields are missing
    val name = remember { mutableStateOf(TextFieldValue()) } // State for the name input
    val city = remember { mutableStateOf("") } // State for the selected city
    val area = remember { mutableStateOf("") } // State for the selected area

    // Extract values from the UI state
    val isError = state.isError
    val cities = state.cityList
    val areaList = state.areaList
    val isLoading = state.isLoading
    val isAdded = state.isAddSuccess
    val errorMessage = state.errorMessage

    // Add visual effects to the screen
    GlassComponent() // Apply glass-like effect to the screen background
    SetBarColors() // Set the status and navigation bar colors

    // Render the ProfileScreenContent composable with the current state and callbacks
    ProfileScreenContent(city = city, // The selected city
        area = area, // The selected area
        name = name, // The entered name
        cities = cities, // List of cities available
        areas = areaList, // List of areas available based on the selected city
        isError = isError, // Flag to indicate if there's an error
        address = address, // The entered address
        isLoading = isLoading, // Flag to indicate if the screen is loading
        checkField = checkField, // Flag to track field validation
        phoneNumber = phoneNumber, // The entered phone number
        expandedCity = expandedCity, // State controlling city dropdown expansion
        expandedArea = expandedArea, // State controlling area dropdown expansion
        errorMessage = errorMessage, // Error message to display
        isAddedSuccessful = isAdded, // Flag indicating if the profile was added successfully
        selectedAreaIndex = selectedAreaIndex, // Selected area index
        selectedCityIndex = selectedCityIndex, // Selected city index
        navigateBack = navigateBack, // Callback to navigate back
        navigateToAddress = navigateToAddress, // Callback to navigate to the address list
        navigateToContactUs = navigateToContactUs, // Callback to navigate to the contact us screen
        fetchAreaList = { cityName -> // Fetch the list of areas for a selected city
            viewModel.fetchAreaList(cityName)
        }, hideError = { viewModel.showError(false) }, // Callback to hide the error message
        addProfile = { names, phoneNumbers, cities, areas, addresses -> // Callback to add a new profile
            viewModel.addNewProfile(
                name = names,
                phoneNumber = phoneNumbers,
                city = cities,
                area = areas,
                address = addresses,
                email = null // Email is optional, passing null
            )
        })
}