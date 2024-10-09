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

/**
 * Composable that represents the content of the history screen.
 * It includes a top bar, a toggle button to filter orders, and a list of orders.
 *
 * @param userId The ID of the current user.
 * @param list The list of all orders made by the user.
 * @param productList A mutable state holding the filtered list of orders.
 * @param isLoading A flag indicating if data is currently being loaded.
 * @param isError A flag indicating if an error has occurred.
 * @param errorMessage The error message to display when an error occurs.
 * @param indexValue A mutable state representing the selected filter index.
 * @param navigateUp Function to navigate back to the previous screen.
 * @param navigateToContactUs Function to navigate to the Contact Us screen.
 * @param getOrdersHistory A function to fetch the order history for the user.
 * @param onErrorClose A function to close the error dialog when it's dismissed.
 */
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
    // Trigger order history retrieval when the composable is first launched
    LaunchedEffect(key1 = Unit) {
        getOrdersHistory(userId)
    }

    // Display error dialog if there's an error
    if (isError) {
        ErrorQueryDialog(
            showDialog = { onErrorClose() },
            callback = {},
            error = errorMessage
        )
    }

    // Display loading dialog if data is being fetched
    if (isLoading) {
        LoadingDialog()
    }

    // Scaffold provides a layout structure with a top bar and main content area
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()  // Add padding to avoid content overlapping the status bar
            .navigationBarsPadding(),  // Add padding to avoid content overlapping navigation bar
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
        // Column layout to structure the toggle button and order list vertically
        Column(modifier = Modifier.padding(padding)) {
            // Toggle button to filter orders by status
            ToggleButton { index ->
                when (index) {
                    0 -> { // Show all orders
                        indexValue.intValue = 0
                        productList.value = list
                    }
                    1 -> { // Filter for "In Progress" or "Confirmed" orders
                        indexValue.intValue = 1
                        productList.value = list.filter {
                            it.orderStatus == "In Progress" || it.orderStatus?.contains("Confirmed") == true
                        }
                    }
                    2 -> { // Filter for "Delivered" orders
                        indexValue.intValue = 2
                        productList.value = list.filter { it.orderStatus == "Delivered" }
                    }
                    3 -> { // Filter for "Cancelled" orders
                        indexValue.intValue = 3
                        productList.value = list.filter { it.orderStatus == "Cancelled" }
                    }
                }
            }

            // Display the filtered or full list of orders depending on the selected filter
            OrderList(list = if (indexValue.intValue == 0) list else productList.value)
        }
    }
}