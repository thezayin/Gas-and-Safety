package com.thezayin.lpg.presentation.admin.fetchOrders.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.lpg.presentation.admin.fetchOrders.domain.model.FetchOrdersModel

@Composable
fun FetchOrdersList(
    modifier: Modifier,
    list: List<FetchOrdersModel>,
    onClick: () -> Unit
) {

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .padding(top = 10.dp),
    ) {
        items(list.size, key = {
            list[it].orderDateTime.toString()
        }) { position ->
            FetchOrdersDetails(
                order = list[position],
                onClick = { onClick() }
            )
        }
    }
}