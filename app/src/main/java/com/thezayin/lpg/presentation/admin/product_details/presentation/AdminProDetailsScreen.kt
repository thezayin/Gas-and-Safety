@file:Suppress("NAME_SHADOWING")

package com.thezayin.lpg.presentation.admin.product_details.presentation

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.lpg.R
import com.thezayin.lpg.common.component.AdminTopBar
import com.thezayin.lpg.common.component.GlassComponent
import com.thezayin.lpg.common.dialogs.ErrorQueryDialog
import com.thezayin.lpg.common.dialogs.FieldsDialog
import com.thezayin.lpg.common.dialogs.LoadingDialog
import com.thezayin.lpg.common.dialogs.NetworkDialog
import com.thezayin.lpg.common.dialogs.SuccessQueryDialog
import com.thezayin.lpg.presentation.admin.addProducts.presentation.components.AddProductDetails
import com.thezayin.lpg.presentation.admin.addProducts.presentation.components.ProductSaveButton
import com.thezayin.lpg.presentation.admin.product_details.presentation.components.AdminDeleteProduct
import com.thezayin.lpg.presentation.admin.product_details.presentation.components.ImageComponent
import org.koin.compose.koinInject

@Composable
@Destination
fun AdminProDetailsScreen(
    navigator: DestinationsNavigator,
    id: String,
    name: String,
    description: String,
    price: String,
    imageUri: Uri
) {
    val viewModel: AdminProDetailsViewModel = koinInject()
    val image = viewModel.image.collectAsState().value.image
    var checkNetwork by remember { mutableStateOf(false) }
    val name = remember { mutableStateOf(TextFieldValue(name)) }
    val description = remember { mutableStateOf(TextFieldValue(description)) }
    val type = remember { mutableStateOf(TextFieldValue()) }
    val price = remember { mutableStateOf(TextFieldValue(price)) }
    var checkField by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isSuccessful = viewModel.isQuerySuccessful.collectAsState().value.isSuccess
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

    if (isSuccessful) {
        SuccessQueryDialog(
            showDialog = { isSuccessful = it },
            callback = { navigator.navigateUp() })
    }

    if (checkField) {
        FieldsDialog(showDialog = { checkField = it })
    }
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            AdminTopBar(
                modifier = Modifier.navigationBarsPadding(),
                navigator = navigator,
                screenTitle = "Product Details"
            )
        },
        bottomBar = {
            ProductSaveButton {
                if (name.value.text.isEmpty() || description.value.text.isEmpty() || price.value.text.isEmpty() || image == null) {
                    checkField = true
                } else {
                    viewModel.updateProduct(
                        id = id,
                        name = name.value.text,
                        description = description.value.text,
                        price = price.value.text
                    )
                }
            }
        }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(top = 30.dp)
            ) {
                AdminDeleteProduct(viewModel = viewModel, id = id)
                ImageComponent(oldUri = imageUri, imageUri = image, viewModel = viewModel)
                AddProductDetails(
                    name = name,
                    description = description,
                    type = type,
                    price = price
                )
            }
        }
    }
}