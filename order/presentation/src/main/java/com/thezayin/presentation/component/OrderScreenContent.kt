package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.thezayin.assets.R
import com.thezayin.common.component.UserTopBar
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.FieldsDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.databases.model.ProfileModel

@Composable
fun OrderScreenContent(
    isError: Boolean,
    isLoading: Boolean,
    isSuccessful: Boolean,
    cities: List<String>,
    areas: List<String>,
    errorMessage: String,
    phoneNumber: MutableState<TextFieldValue>,
    address: MutableState<TextFieldValue>,
    selectedCityIndex: MutableState<Int>,
    expandedCity: MutableState<Boolean>,
    expandedArea: MutableState<Boolean>,
    name: MutableState<TextFieldValue>,
    selectedAreaIndex: MutableIntState,
    checkField: MutableState<Boolean>,
    city: MutableState<String>,
    area: MutableState<String>,
    list: List<ProfileModel>?,
    selectedIndex: MutableState<Int>,
    navigateUp: () -> Unit,
    hideError: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToProfile: () -> Unit,
    onItemSelected: (Int) -> Unit,
    navigateToContactUs: () -> Unit,
    fetchAreaList: (String) -> Unit,
    placeOrder: (String, String, String, String, String) -> Unit,
    addNewProfile: (String, String, String, String, String) -> Unit,
) {

    if (checkField.value) FieldsDialog(showDialog = { checkField.value = it })
    if (isLoading) LoadingDialog()
    if (isSuccessful) navigateToHome()

    if (isError) {
        ErrorQueryDialog(
            showDialog = { hideError() },
            callback = { navigateUp() },
            error = errorMessage
        )
    }

    Scaffold(modifier = Modifier.navigationBarsPadding().statusBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(modifier = Modifier,
                screen = "Select Address",
                onBackClick = { navigateUp() },
                onContactClick = { navigateToContactUs() })
        },
        bottomBar = {
            OrderSubmitButton {
                if (name.value.text.isEmpty() || phoneNumber.value.text.isEmpty() || city.value.isEmpty() || area.value.isEmpty() || address.value.text.isEmpty()) {
                    checkField.value = true
                } else {
                    placeOrder(
                        name.value.text,
                        phoneNumber.value.text,
                        city.value,
                        area.value,
                        address.value.text
                    )
                    if (list.isNullOrEmpty()) {
                        addNewProfile(
                            name.value.text,
                            phoneNumber.value.text,
                            city.value,
                            area.value,
                            address.value.text
                        )
                    }
                }
            }
        }) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxWidth().fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (list.isNullOrEmpty()) {
                Column(
                    modifier = Modifier.padding(padding).fillMaxWidth().fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        UserOrderDetails(
                            name = name,
                            phoneNumber = phoneNumber,
                        )
                        LocationOrderDetails(
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
                                fetchAreaList(it)
                            },
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier.padding(horizontal = 25.dp).fillMaxSize()
                ) {
                    AddOrderProfileButton {
                        navigateToProfile()
                    }
                    SelectAddressList(
                        profileList = list,
                        modifier = Modifier,
                        selectedIndex = selectedIndex.value
                    ) { profile, index ->
                        name.value = TextFieldValue(profile.name!!)
                        phoneNumber.value = TextFieldValue(profile.phoneNumber!!)
                        address.value = TextFieldValue(profile.address!!)
                        city.value = profile.city!!
                        area.value = profile.area!!
                        selectedIndex.value = index
                        onItemSelected(index)
                    }
                }
            }
        }
    }
}