package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.assets.R
import com.thezayin.common.component.UserTopBar
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.domain.model.OrderModel

@Composable
fun HistoryScreenContent(
    userId: String,
    list: List<OrderModel>,
    productList: MutableState<List<OrderModel>>,
    isLoading: Boolean,
    isError: Boolean,
    errorMessage: String,
    indexValue: MutableIntState,
    navigateUp: () -> Unit,
    navigateToContactUs: () -> Unit,
    getOrdersHistory: (String) -> Unit,
    onErrorClose: () -> Unit
) {
    LaunchedEffect(key1 = Unit) { getOrdersHistory(userId) }
    if (isError) {
        ErrorQueryDialog(showDialog = { onErrorClose() }, callback = {}, error = errorMessage)
    }

    if (isLoading) {
        LoadingDialog()
    }
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(
                modifier = Modifier,
                screen = "Order History",
                onBackClick = { navigateUp() },
                onContactClick = { navigateToContactUs() }
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