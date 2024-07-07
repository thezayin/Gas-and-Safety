package com.thezayin.lpg.presentation.users.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.lpg.R
import com.thezayin.lpg.common.component.GlassComponent
import com.thezayin.lpg.common.component.UserTopBar
import com.thezayin.lpg.common.dialogs.ErrorQueryDialog
import com.thezayin.lpg.common.dialogs.LoadingDialog
import com.thezayin.lpg.common.dialogs.NetworkDialog
import com.thezayin.lpg.destinations.ProfileScreenDestination
import com.thezayin.lpg.presentation.users.profile.component.AddProfileButton
import com.thezayin.lpg.presentation.users.profile.component.ProfileList
import org.koin.compose.koinInject

@Composable
@Destination
fun AddressScreen(
    navigator: DestinationsNavigator
) {

    val viewModel: ProfileViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val errorMessage = viewModel.isQueryError.collectAsState().value.errorMessage
    val list = viewModel.getProfileList.collectAsState().value.data

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
        modifier = Modifier,
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(
                modifier = Modifier,
                navigator = navigator,
                screen = "Profile"
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .padding(padding)
                .fillMaxWidth()
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AddProfileButton {
                navigator.navigate(ProfileScreenDestination)
            }
            ProfileList(profileList = list, onDeleteClick = {
                viewModel.deleteProfile(it)
            })
        }
    }
}