package com.thezayin.lpg.presentation.admin.addProducts.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.thezayin.lpg.presentation.admin.addProducts.presentation.components.AddImage
import com.thezayin.lpg.presentation.admin.addProducts.presentation.components.AddProductDetails
import com.thezayin.lpg.presentation.admin.addProducts.presentation.components.ProductSaveButton
import org.koin.compose.koinInject

@Composable
@Destination
fun AddProductScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: AddProductViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val name = remember { mutableStateOf(TextFieldValue()) }
    val description = remember { mutableStateOf(TextFieldValue()) }
    val type = remember { mutableStateOf(TextFieldValue()) }
    val price = remember { mutableStateOf(TextFieldValue()) }
    var checkField by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isSuccessful = viewModel.isQuerySuccessful.collectAsState().value.isSuccess
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage

    val imageUri = viewModel.image.collectAsState().value.image
    val imageSelected = remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            viewModel.updateImage(uri)
            imageSelected.value = true
        }
    )

    GlassComponent()

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (checkField) {
        FieldsDialog(showDialog = { checkField = it })
    }

    if (isLoading) {
        LoadingDialog()
    }

    if (isSuccessful) {
        SuccessQueryDialog(
            showDialog = { isSuccessful = it },
            callback = { navigator.navigateUp() })
    }

    if (isError) {
        ErrorQueryDialog(
            showDialog = { isError = it },
            callback = { navigator.navigateUp() },
            error = errorMessage
        )
    }

    Scaffold(modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            AdminTopBar(
                modifier = Modifier.navigationBarsPadding(),
                navigator = navigator,
                screenTitle = "Add Product"
            )
        },
        bottomBar = {
            ProductSaveButton {
                if (name.value.text.isEmpty() || description.value.text.isEmpty() || price.value.text.isEmpty() || imageUri == null) {
                    checkField = true
                } else {
                    viewModel.addProduct(
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
                .fillMaxWidth()
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(top = 30.dp)
            ) {
                AddImage(
                    imageUri = imageUri,
                    imageSelected = imageSelected,
                ) {
                    launcher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
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
