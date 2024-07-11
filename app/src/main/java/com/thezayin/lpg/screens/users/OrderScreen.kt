package com.thezayin.lpg.screens.users

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.UserTopBar
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.FieldsDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.framework.extension.functions.getUserUUID
import com.thezayin.lpg.R
import com.thezayin.lpg.destinations.ContactUsScreenDestination
import com.thezayin.lpg.destinations.HomeScreenDestination
import com.thezayin.lpg.destinations.ProfileScreenDestination
import com.thezayin.useraddress.presentation.ProfileViewModel
import com.thezayin.useraddress.presentation.component.AddProfileButton
import com.thezayin.useraddress.presentation.component.LocationDetails
import com.thezayin.useraddress.presentation.component.UserDetails
import com.thezayin.userbuy.presentation.OrderViewModel
import com.thezayin.userbuy.presentation.component.OrderSubmitButton
import com.thezayin.userbuy.presentation.component.SelectAddressList
import org.koin.compose.koinInject

@Composable
@Destination
fun OrderScreen(
    navigator: DestinationsNavigator,
    totalAmount: String,
) {
    val viewModel: OrderViewModel = koinInject()
    val addressViewModel: ProfileViewModel = koinInject()
    val addressList = addressViewModel.getProfileList.collectAsState().value.data

    val cities = addressViewModel.getCityState.collectAsState().value.data
    val areas = addressViewModel.getAreaState.collectAsState().value.data
    val selectedCityIndex = remember { mutableIntStateOf(0) }
    val selectedAreaIndex = remember { mutableIntStateOf(0) }
    val phoneNumber = remember { mutableStateOf(TextFieldValue()) }
    var checkNetwork by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(-1) }
    val address = remember { mutableStateOf(TextFieldValue()) }
    val expandedCity = remember { mutableStateOf(false) }
    val expandedArea = remember { mutableStateOf(false) }
    var checkField by remember { mutableStateOf(false) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val name = remember { mutableStateOf(TextFieldValue()) }
    val city = remember { mutableStateOf("") }
    val area = remember { mutableStateOf("") }

    val isSuccessful = viewModel.isQuerySuccessful.collectAsState().value.isSuccess
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val activity = LocalContext.current as Activity

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
    if (isSuccessful) {
        navigator.navigate(HomeScreenDestination)
    }
    if (isError) {
        ErrorQueryDialog(
            showDialog = { isError = it },
            callback = { navigator.navigateUp() },
            error = errorMessage
        )
    }

    Scaffold(modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(
                modifier = Modifier,
                screen = "Select Address",
                onBackClick = { navigator.navigateUp() },
                onContactClick = { navigator.navigate(ContactUsScreenDestination) }
            )
        },
        bottomBar = {
            OrderSubmitButton {
                if (name.value.text.isEmpty() || phoneNumber.value.text.isEmpty() || email.value.text.isEmpty() || city.value.isEmpty() || area.value.isEmpty() || address.value.text.isEmpty()) {
                    checkField = true
                } else {
                    viewModel.placeAllOrders(
                        userID = activity.getUserUUID(),
                        name = name.value.text,
                        phoneNumber = phoneNumber.value.text,
                        email = email.value.text,
                        address = address.value.text,
                        area = area.value,
                        city = city.value,
                        message = "",
                        totalAmount = totalAmount,
                    )
                    if (addressList.isEmpty()) {
                        addressViewModel.addNewProfile(
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
        }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (addressList.isEmpty()) {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxWidth()
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
                                addressViewModel.fetchAreaList(it)
                            },
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .fillMaxSize()
                ) {
                    AddProfileButton {
                        navigator.navigate(ProfileScreenDestination)
                    }
                    SelectAddressList(
                        profileList = addressList,
                        modifier = Modifier,
                        selectedIndex = selectedIndex
                    ) { profile, index ->
                        name.value = TextFieldValue(profile.name!!)
                        phoneNumber.value = TextFieldValue(profile.phoneNumber!!)
                        email.value = TextFieldValue(profile.email!!)
                        address.value = TextFieldValue(profile.address!!)
                        city.value = profile.city!!
                        area.value = profile.area!!
                        selectedIndex = index
                        addressViewModel.onItemSelected(index)
                    }
                }
            }
        }
    }
}