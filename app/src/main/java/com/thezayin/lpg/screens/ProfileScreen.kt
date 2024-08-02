package com.thezayin.lpg.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.UserTopBar
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.FieldsDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.lpg.R
import com.thezayin.lpg.screens.destinations.AddressScreenDestination
import com.thezayin.lpg.screens.destinations.ContactUsScreenDestination
import com.thezayin.useraddress.presentation.ProfileViewModel
import com.thezayin.useraddress.presentation.component.LocationDetails
import com.thezayin.useraddress.presentation.component.SaveProfileButton
import com.thezayin.useraddress.presentation.component.UserDetails
import org.koin.compose.koinInject

@Composable
@Destination
fun ProfileScreen(
    navigator: DestinationsNavigator
) {

    val viewModel: ProfileViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    var checkField by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage
    val isAddedSuccessful = viewModel.isAddSuccess.collectAsState().value.isSuccess

    val cities = viewModel.getCityState.collectAsState().value.data
    val areas = viewModel.getAreaState.collectAsState().value.data
    val selectedCityIndex = remember { mutableIntStateOf(0) }
    val selectedAreaIndex = remember { mutableIntStateOf(0) }
    val expandedCity = remember { mutableStateOf(false) }
    val expandedArea = remember { mutableStateOf(false) }
    val name = remember { mutableStateOf(TextFieldValue()) }
    val phoneNumber = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val address = remember { mutableStateOf(TextFieldValue()) }
    val city = remember { mutableStateOf("") }
    val area = remember { mutableStateOf("") }

    GlassComponent()

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }
    if (checkField) {
        FieldsDialog(showDialog = { checkField = it })
    }
    if (isLoading) {
        LoadingDialog()
    }
    if (isAddedSuccessful) {
        navigator.navigate(AddressScreenDestination)
    }
    if (isError) {
        ErrorQueryDialog(
            showDialog = { isError = it },
            callback = { navigator.navigateUp() },
            error = errorMessage
        )
    }

    Scaffold(
        modifier = Modifier,
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(
                modifier = Modifier,
                screen = "Profile",
                onBackClick = { navigator.navigateUp() },
                onContactClick = { navigator.navigate(ContactUsScreenDestination) }
            )
        },
        bottomBar = {
            SaveProfileButton {
                if (name.value.text.isEmpty() || phoneNumber.value.text.isEmpty() || email.value.text.isEmpty() || city.value.isEmpty() || area.value.isEmpty() || address.value.text.isEmpty()) {
                    checkField = true
                } else {
                    viewModel.addNewProfile(
                        name = name.value.text,
                        phoneNumber = phoneNumber.value.text,
                        email = email.value.text,
                        city = city.value,
                        area = area.value,
                        address = address.value.text
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .fillMaxWidth()
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier) {
                UserDetails(
                    email = email,
                    name = name,
                    phoneNumber = phoneNumber,
                )
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
                        viewModel.fetchAreaList(it)
                    },
                )
            }
        }
    }
}