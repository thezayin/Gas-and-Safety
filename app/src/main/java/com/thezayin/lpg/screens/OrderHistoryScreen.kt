package com.thezayin.lpg.screens

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.UserTopBar
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.framework.extension.functions.getUserUUID
import com.thezayin.lpg.R
import com.thezayin.lpg.screens.destinations.ContactUsScreenDestination
import com.thezayin.userorderhistory.presentation.OrderHistoryViewModel
import com.thezayin.userorderhistory.presentation.component.OrderList
import com.thezayin.userorderhistory.presentation.component.ToggleButton
import org.koin.compose.koinInject

@Destination
@Composable
fun OrderHistoryScreen(navigator: DestinationsNavigator) {
    val viewModel: OrderHistoryViewModel = koinInject()
    val activity = LocalContext.current as Activity
    val userId = activity.getUserUUID()
    val list = viewModel.getOrdersHistory.collectAsState().value.list
    val productList = remember { mutableStateOf(list) }
    val indexValue = remember { mutableIntStateOf(0) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    LaunchedEffect(key1 = Unit) { viewModel.getOrdersHistory(userId) }
    if (isLoading) {
        LoadingDialog()
    }
    GlassComponent()

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
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            ToggleButton { index ->
                when (index) {
                    0 -> {
                        indexValue.intValue = 0
                        productList.value = list
                    }

                    1 -> {
                        indexValue.intValue = 1
                        productList.value = list.filter {
                            it.orderStatus == "In Progress" || it.orderStatus?.contains("Confirmed") == true
                        }
                    }

                    2 -> {
                        indexValue.intValue = 2
                        productList.value = list.filter { it.orderStatus == "Delivered" }
                    }

                    3 -> {
                        indexValue.intValue = 3
                        productList.value = list.filter { it.orderStatus == "Cancelled" }
                    }
                }
            }
            OrderList(list = if (indexValue.intValue == 0) list else productList.value)
        }
    }
}