package com.thezayin.lpg.screens.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.adminhome.presentation.AdminHomeViewModel
import com.thezayin.adminhome.presentation.components.AdminHomeTopBar
import com.thezayin.lpg.R
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.lpg.destinations.AddProductScreenDestination
import com.thezayin.lpg.destinations.AdminProductScreenDestination
import com.thezayin.lpg.destinations.FetchOrdersScreenDestination
import com.thezayin.lpg.destinations.SettingScreenDestination
import com.thezayin.adminhome.presentation.components.AdminMenu
import org.koin.compose.koinInject

@Composable
@Destination
fun AdminHomeScreen(navigator: DestinationsNavigator) {
    val viewModel: AdminHomeViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val optionList = viewModel.adminHomeOptions.collectAsState().value.list
    if (checkNetwork) {
        com.thezayin.common.dialogs.NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        com.thezayin.common.dialogs.LoadingDialog()
    }

    com.thezayin.common.component.GlassComponent()
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),

        topBar = {
            AdminHomeTopBar(modifier = Modifier, backClick = {
                navigator.navigate(
                    SettingScreenDestination
                )
            })
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            AdminMenu(
                modifier = Modifier,
                list = optionList,
                callBack = { option ->
                    when (option.id) {
                        1 -> {
                            navigator.navigate(AdminProductScreenDestination)
                        }

                        2 -> {
                            navigator.navigate(AddProductScreenDestination)
                        }

                        3 -> {
                            navigator.navigate(FetchOrdersScreenDestination)
                        }
                    }
                }
            )
        }
    }
}