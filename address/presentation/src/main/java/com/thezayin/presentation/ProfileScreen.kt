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

@Composable
fun ProfileScreen(
    navigateBack: () -> Unit = {},
    navigateToAddress: () -> Unit,
    navigateToContactUs: () -> Unit,
) {
    val viewModel: ProfileViewModel = koinInject()
    val state = viewModel.addressUiState.collectAsState().value

    val selectedCityIndex = remember { mutableIntStateOf(0) }
    val selectedAreaIndex = remember { mutableIntStateOf(0) }
    val phoneNumber = remember { mutableStateOf(TextFieldValue()) }
    val address = remember { mutableStateOf(TextFieldValue()) }
    val expandedCity = remember { mutableStateOf(false) }
    val expandedArea = remember { mutableStateOf(false) }
    val checkField = remember { mutableStateOf(false) }
    val name = remember { mutableStateOf(TextFieldValue()) }
    val city = remember { mutableStateOf("") }
    val area = remember { mutableStateOf("") }

    val isError = state.isError
    val cities = state.cityList
    val areaList = state.areaList
    val isLoading = state.isLoading
    val isAdded = state.isAddSuccess
    val errorMessage = state.errorMessage

    GlassComponent()
    SetBarColors()

    ProfileScreenContent(
        city = city,
        area = area,
        name = name,
        cities = cities,
        areas = areaList,
        isError = isError,
        address = address,
        isLoading = isLoading,
        checkField = checkField,
        phoneNumber = phoneNumber,
        expandedCity = expandedCity,
        expandedArea = expandedArea,
        errorMessage = errorMessage,
        isAddedSuccessful = isAdded,
        selectedAreaIndex = selectedAreaIndex,
        selectedCityIndex = selectedCityIndex,
        navigateBack = navigateBack,
        navigateToAddress = navigateToAddress,
        navigateToContactUs = navigateToContactUs,
        fetchAreaList = { viewModel.fetchAreaList(it) },
        hideError = { viewModel.showError(false) },
        addProfile = { names, phoneNumbers, dist, areas, addresses ->
            viewModel.addNewProfile(
                name = names,
                phoneNumber = phoneNumbers,
                city = dist,
                area = areas,
                address = addresses,
                email = null
            )
        }
    )
}