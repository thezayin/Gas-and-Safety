package com.thezayin.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.domain.model.OrderModel
import ir.kaaveh.sdpcompose.sdp

/**
 * A composable that displays a list of orders in a scrollable view.
 * Each order is represented by the HistoryCard composable.
 *
 * @param list A list of OrderModel objects representing the user's orders.
 */
@Composable
fun OrderList(
    list: List<OrderModel>
) {
    // LazyColumn for efficiently displaying a scrollable list of orders.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize() // Ensures the list takes up the available screen space.
            .padding(vertical = 20.sdp, horizontal = 15.sdp) // Padding around the list for better spacing.
    ) {
        // Sort the list by orderDateTime in descending order to show recent orders first.
        val sortedList = list.sortedByDescending { it.orderDateTime.toString() }

        // Display each order using HistoryCard.
        items(sortedList.size) { index ->
            HistoryCard(sortedList[index]) // Pass the sorted orders to the HistoryCard composable.
        }
    }
}