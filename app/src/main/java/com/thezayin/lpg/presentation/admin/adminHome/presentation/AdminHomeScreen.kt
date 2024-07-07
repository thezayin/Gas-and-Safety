package com.thezayin.lpg.presentation.admin.adminHome.presentation

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
import com.thezayin.lpg.R
import com.thezayin.lpg.common.component.GlassComponent
import com.thezayin.lpg.common.dialogs.LoadingDialog
import com.thezayin.lpg.common.dialogs.NetworkDialog
import com.thezayin.lpg.presentation.admin.adminHome.presentation.component.AdminHomeTopBar
import com.thezayin.lpg.presentation.admin.adminHome.presentation.component.AdminMenu
import org.koin.compose.koinInject

@Composable
@Destination
fun AdminHomeScreen(navigator: DestinationsNavigator) {
    val viewModel: AdminHomeViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val optionList = viewModel.adminHomeOptions.collectAsState().value.list
    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog()
    }

    GlassComponent()
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),

        topBar = {
            AdminHomeTopBar(modifier = Modifier, navigator = navigator)
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            AdminMenu(
                modifier = Modifier,
                navigator = navigator,
                list = optionList
            )
        }
    }
}