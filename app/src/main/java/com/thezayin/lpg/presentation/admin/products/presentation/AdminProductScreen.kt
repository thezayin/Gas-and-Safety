package com.thezayin.lpg.presentation.admin.products.presentation

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
import com.thezayin.lpg.common.activities.MainViewModel
import com.thezayin.lpg.common.component.AdminTopBar
import com.thezayin.lpg.common.component.GlassComponent
import com.thezayin.lpg.common.dialogs.ErrorQueryDialog
import com.thezayin.lpg.common.dialogs.LoadingDialog
import com.thezayin.lpg.common.dialogs.NetworkDialog
import com.thezayin.lpg.presentation.admin.products.presentation.components.AddProductButton
import com.thezayin.lpg.presentation.admin.products.presentation.components.AdminProductList
import org.koin.compose.koinInject

@Composable
@Destination
fun AdminProductScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: AdminProductViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage

    GlassComponent()

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog()
    }

    if (isError) {
        ErrorQueryDialog(
            showDialog = { isError = it },
            callback = { navigator.navigateUp() },
            error = errorMessage
        )
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),

        topBar = {
            AdminTopBar(
                modifier = Modifier,
                navigator = navigator,
                screenTitle = "Products"
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            AddProductButton(navigator)
            AdminProductList(
                modifier = Modifier,
                viewModel = viewModel,
                navigator = navigator
            )
        }
    }
}