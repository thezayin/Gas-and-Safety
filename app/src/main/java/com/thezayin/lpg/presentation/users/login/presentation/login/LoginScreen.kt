package com.thezayin.lpg.presentation.users.login.presentation.login

import android.util.Log
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
import com.thezayin.lpg.common.dialogs.LoadingDialog
import com.thezayin.lpg.common.dialogs.LoginErrorDialog
import com.thezayin.lpg.common.dialogs.NetworkDialog
import com.thezayin.lpg.destinations.VerifyOTPScreenDestination
import com.thezayin.lpg.presentation.users.login.presentation.LoginViewModel
import com.thezayin.lpg.presentation.users.login.presentation.login.component.EnterNumber
import com.thezayin.lpg.presentation.users.login.presentation.login.component.LoginBottomButton
import org.koin.compose.koinInject

@Composable
@Destination
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: LoginViewModel = koinInject()
    val mainViewModel: MainViewModel = koinInject()
    val number = remember { mutableStateOf(TextFieldValue()) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading.value
    var isSuccessful = viewModel.isQuerySuccessful.collectAsState().value.isSuccess.value
    var error = viewModel.isQueryError.collectAsState().value.isError.value
//    var isError by remember { mutableStateOf(error) }
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage.value
    var checkNetwork by remember { mutableStateOf(false) }

    GlassComponent()
    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog()
    }

    if (isSuccessful) {
        navigator.navigate(VerifyOTPScreenDestination(number = "+92${number.value.text}"))
    }

    if (error) {
        LoginErrorDialog(
            showDialog = {
                viewModel.updateErrorState(it)
            },
            error = errorMessage
        )
    }

    Scaffold(
        modifier = Modifier,
        containerColor = colorResource(id = R.color.semi_transparent),
        bottomBar = {
            LoginBottomButton(
                modifier = Modifier,
                navigator = navigator,
                viewModel = viewModel,
                number = "+92${number.value.text}"
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 25.dp)
        ) {
            EnterNumber(number = number)
        }
    }
}
