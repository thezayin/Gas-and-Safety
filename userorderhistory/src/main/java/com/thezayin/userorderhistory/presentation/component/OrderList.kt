package com.thezayin.userorderhistory.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.entities.OrderModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun OrderList(
    list: List<OrderModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.sdp, horizontal = 15.sdp)
    ) {
        val sortedList = list.sortedByDescending { it.orderDateTime.toString() }
        items(sortedList.size) { orders ->
            HistoryCard(list[orders])
        }
    }
}