package com.thezayin.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.framework.extension.getUserUUID
import com.thezayin.presentation.component.OrderScreenContent
import org.koin.compose.koinInject

@Composable
fun OrderScreen(
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToContactUs: () -> Unit
) {
    val viewModel: OrderViewModel = koinInject()
    val state = viewModel.orderUiState.collectAsState().value
    val totalAmount = state.orderList.sumBy { it.totalPrice?.toInt()!! }
    val activity = LocalContext.current as Activity

    val userId = activity.getUserUUID()
    val isLoading = state.isLoading
    val isError = state.isError
    val errorMessage = state.errorMessage
    val orderList = state.orderList
    val orderSuccess = state.orderSuccess
    val isDeleteSuccess = state.isDeleteSuccess
    val profileList = state.getAddresses
    val cityList = state.cityList
    val areaList = state.areaList

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

    GlassComponent()
    SetBarColors()

    OrderScreenContent(
        isError = isError,
        isLoading = isLoading,
        isSuccessful = orderSuccess,
        cities = cityList,
        areas = areaList,
        errorMessage = errorMessage,
        phoneNumber = phoneNumber,
        address = address,
        selectedCityIndex = selectedCityIndex,
        expandedCity = expandedCity,
        expandedArea = expandedArea,
        name = name,
        selectedAreaIndex = selectedAreaIndex,
        checkField = checkField,
        city = city,
        area = area,
        list = profileList,
        selectedIndex = selectedCityIndex,
        navigateUp = { navigateUp() },
        hideError = { viewModel.isError(false) },
        navigateToHome = { navigateToHome() },
        navigateToProfile = { navigateToProfile() },
        onItemSelected = { },
        navigateToContactUs = { navigateToContactUs() },
        fetchAreaList = { viewModel.fetchAreaList(it) },
        placeOrder = { newName, newNumber, newAddress, newArea, newCity ->
            viewModel.placeAllOrders(
                userId,
                newName,
                newNumber,
                newAddress,
                newArea,
                newCity,
                totalAmount.toString(),
                orderList
            )
        },
        addNewProfile = { newName, newNumber, newAddress, newArea, newCity ->
            viewModel.addNewProfile(newName, newNumber, newAddress, newArea, newCity)
        },
    )
}