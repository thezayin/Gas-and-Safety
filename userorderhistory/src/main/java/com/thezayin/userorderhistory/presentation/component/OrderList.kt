package com.thezayin.userorderhistory.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.OrderModel

@Composable
fun OrderList(
    list: List<OrderModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp, horizontal = 25.dp)
    ) {
        val sortedList = list.sortedByDescending { it.orderDateTime.toString() }
        items(sortedList.size) { orders ->
            HistoryCard(list[orders])
        }
    }
}