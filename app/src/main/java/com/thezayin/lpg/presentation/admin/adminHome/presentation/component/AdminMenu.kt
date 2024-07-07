package com.thezayin.lpg.presentation.admin.adminHome.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.lpg.presentation.admin.adminHome.domain.model.AdminOptionMenuModel

@Composable
fun AdminMenu(
    modifier: Modifier,
    navigator: DestinationsNavigator,
    list: List<AdminOptionMenuModel>
) {

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .padding(top = 25.dp),
    ) {
        items(list.size) { option ->
            AdminMenuItem(
                option = list[option],
                modifier = Modifier,
                navigator = navigator,
            )
        }
    }
}