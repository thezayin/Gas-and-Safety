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
import com.thezayin.adminproducts.presentation.AdminProductViewModel
import com.thezayin.adminproducts.presentation.component.AddProductButton
import com.thezayin.lpg.R
import com.thezayin.common.component.AdminTopBar
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.lpg.destinations.AddProductScreenDestination
import com.thezayin.lpg.destinations.AdminProDetailsScreenDestination
import com.thezayin.adminproducts.presentation.component.AdminProductList
import org.koin.compose.koinInject

@Composable
@Destination
fun AdminProductScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: AdminProductViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val productList = viewModel.getAdminProducts.collectAsState().value.list
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage

    com.thezayin.common.component.GlassComponent()

    if (checkNetwork) {
        com.thezayin.common.dialogs.NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        com.thezayin.common.dialogs.LoadingDialog()
    }

    if (isError) {
        com.thezayin.common.dialogs.ErrorQueryDialog(
            showDialog = { isError = it },
            callback = { navigator.navigateUp() },
            error = errorMessage
        )
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),

        topBar = {
            com.thezayin.common.component.AdminTopBar(
                modifier = Modifier,
                onBackClick = { navigator.navigateUp() },
                screenTitle = "Products"
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            AddProductButton(onClick = { navigator.navigate(AddProductScreenDestination) })
            AdminProductList(
                modifier = Modifier,
                productList = productList,
                onClick = { product ->
                    navigator.navigate(
                        AdminProDetailsScreenDestination(
                            id = product.id ?: "",
                            name = product.name ?: "",
                            description = product.description ?: "",
                            price = product.price ?: "",
                            imageUri = product.imageUri ?: throw IllegalArgumentException(
                                "Image Uri is null"
                            ),
                        )
                    )
                }
            )
        }
    }
}