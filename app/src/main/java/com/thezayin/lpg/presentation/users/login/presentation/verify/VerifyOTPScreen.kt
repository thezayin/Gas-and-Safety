package com.thezayin.lpg.presentation.users.login.presentation.verify

import androidx.compose.foundation.layout.Column
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
import com.thezayin.lpg.common.activities.MainViewModel
import com.thezayin.lpg.common.component.GlassComponent
import com.thezayin.lpg.common.dialogs.ErrorQueryDialog
import com.thezayin.lpg.common.dialogs.LoadingDialog
import com.thezayin.lpg.common.dialogs.NetworkDialog
import com.thezayin.lpg.common.dialogs.SuccessQueryDialog
import com.thezayin.lpg.destinations.HomeScreenDestination
import com.thezayin.lpg.presentation.users.login.presentation.LoginViewModel
import com.thezayin.lpg.presentation.users.login.presentation.verify.component.OTPSubmit
import com.thezayin.lpg.presentation.users.login.presentation.verify.component.VerifyBottomButton
import org.koin.compose.koinInject

@Composable
@Destination
fun VerifyOTPScreen(
    navigator: DestinationsNavigator,
    number: String
) {
    val viewModel: LoginViewModel = koinInject()
    val mainViewModel: MainViewModel = koinInject()
    val otp = remember { mutableStateOf(TextFieldValue()) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading.value
    var isSuccessful = viewModel.isQuerySuccessful.collectAsState().value.isSuccess.value
    var isError = viewModel.isQueryError.collectAsState().value.isError.value
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage.value
    val verificationCode = viewModel.verificationCode.collectAsState().value.verificationCode.value
    var checkNetwork by remember { mutableStateOf(false) }
    GlassComponent()
    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog()
    }

    if (isSuccessful) {
        SuccessQueryDialog(
            showDialog = { isSuccessful = it },
            callback = {
                navigator.navigate(HomeScreenDestination)
            })
    }

    if (isError) {
        ErrorQueryDialog(
            showDialog = { isError = it },
            callback = { navigator.navigateUp() },
            error = errorMessage
        )
    }

    Scaffold(
        modifier = Modifier,
        containerColor = colorResource(id = R.color.semi_transparent),
        bottomBar = {
            VerifyBottomButton(
                modifier = Modifier,
                navigator = navigator,
                viewModel = viewModel,
                otp = otp.value.text,
                verificationCode = verificationCode
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 25.dp)
        ) {
            OTPSubmit(otp = otp)
        }
    }
}