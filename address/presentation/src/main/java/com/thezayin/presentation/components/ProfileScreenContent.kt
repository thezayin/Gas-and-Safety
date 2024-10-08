package com.thezayin.presentation.components

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
import com.thezayin.assets.R
import com.thezayin.common.component.UserTopBar
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.FieldsDialog
import com.thezayin.common.dialogs.LoadingDialog

@Composable
fun ProfileScreenContent(
    isError: Boolean,
    isLoading: Boolean,
    areas: List<String>,
    errorMessage: String,
    cities: List<String>,
    city: MutableState<String>,
    area: MutableState<String>,
    isAddedSuccessful: Boolean,
    selectedAreaIndex: MutableIntState,
    checkField: MutableState<Boolean>,
    name: MutableState<TextFieldValue>,
    expandedCity: MutableState<Boolean>,
    expandedArea: MutableState<Boolean>,
    selectedCityIndex: MutableState<Int>,
    address: MutableState<TextFieldValue>,
    phoneNumber: MutableState<TextFieldValue>,
    hideError: () -> Unit,
    navigateBack: () -> Unit,
    navigateToAddress: () -> Unit,
    navigateToContactUs: () -> Unit,
    fetchAreaList: (String) -> Unit,
    addProfile: (String, String, String, String, String) -> Unit
) {

    if (checkField.value) {
        FieldsDialog(showDialog = { checkField.value = false })
    }
    if (isLoading) {
        LoadingDialog()
    }

    if (isError) {
        ErrorQueryDialog(
            showDialog = { hideError() },
            callback = { },
            error = errorMessage
        )
    }

    Scaffold(modifier = Modifier.statusBarsPadding().navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(modifier = Modifier,
                screen = "Profile",
                onBackClick = { navigateBack() },
                onContactClick = { navigateToContactUs() })
        },
        bottomBar = {
            SaveProfileButton {
                if (name.value.text.isEmpty() || phoneNumber.value.text.isEmpty() || city.value.isEmpty() || area.value.isEmpty() || address.value.text.isEmpty()) {
                    checkField.value = true
                } else {
                    addProfile(
                        name.value.text,
                        phoneNumber.value.text,
                        city.value,
                        area.value,
                        address.value.text
                    )
                    navigateToAddress()
                }
            }
        }) { padding ->
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(padding)
                .fillMaxWidth().fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier) {
                UserDetails(
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
                        fetchAreaList(it)
                    },
                )
            }
        }
    }
}