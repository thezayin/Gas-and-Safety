package com.thezayin.lpg.presentation.users.placeOrder

import android.app.Activity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.framework.extension.functions.getUserUUID
import com.thezayin.lpg.R
import com.thezayin.lpg.common.component.GlassComponent
import com.thezayin.lpg.common.component.UserTopBar
import com.thezayin.lpg.common.dialogs.ErrorQueryDialog
import com.thezayin.lpg.common.dialogs.FieldsDialog
import com.thezayin.lpg.common.dialogs.LoadingDialog
import com.thezayin.lpg.common.dialogs.NetworkDialog
import com.thezayin.lpg.destinations.HomeScreenDestination
import com.thezayin.lpg.presentation.users.placeOrder.component.OrderSubmitButton
import com.thezayin.lpg.presentation.users.placeOrder.component.PersonalDataFields
import com.thezayin.lpg.presentation.users.placeOrder.component.SaveForFuture
import org.koin.compose.koinInject

@Composable
@Destination
fun OrderScreen(
    navigator: DestinationsNavigator,
    totalAmount: String,
) {
    val viewModel: OrderViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val name = remember { mutableStateOf(TextFieldValue()) }
    val number = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val address = remember { mutableStateOf(TextFieldValue()) }
    val message = remember { mutableStateOf(TextFieldValue()) }
    var checkField by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val isSuccessful = viewModel.isQuerySuccessful.collectAsState().value.isSuccess
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage
    val activity = LocalContext.current as Activity
    var checked by remember { mutableStateOf(false) }

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
        navigator.navigate(HomeScreenDestination)
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
            UserTopBar(
                modifier = Modifier.navigationBarsPadding(),
                navigator = navigator,
                screen = "Details"
            )
        },
        bottomBar = {
            OrderSubmitButton {
                if (name.value.text.isEmpty() || number.value.text.isEmpty() || number.value.text.length < 11 || address.value.text.isEmpty()) {
                    checkField = true
                } else {

                    viewModel.placeAllOrders(
                        userID = activity.getUserUUID(),
                        name = name.value.text,
                        phoneNumber = number.value.text,
                        email = email.value.text,
                        address = address.value.text,
                        message = message.value.text,
                        totalAmount = totalAmount,
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
                PersonalDataFields(
                    name = name,
                    number = number,
                    email = email,
                    address = address,
                    message = message
                )
                SaveForFuture(checked) { boolean ->
                    checked = boolean
                }
            }
        }
    }
}