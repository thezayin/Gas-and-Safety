package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.thezayin.assets.R
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.common.component.UserTopBar
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.databases.model.ProfileModel

@Composable
fun AddressScreenContent(
    isLoading: Boolean,
    isError: Boolean,
    errorMessage: String,
    list: List<ProfileModel>?,
    hideError: () -> Unit,
    navigateBack: () -> Unit,
    navigateToContactUs: () -> Unit,
    deleteProfile: (Int) -> Unit,
    navigateToProfile: () -> Unit,
) {

    GlassComponent()
    SetBarColors()

    if (isLoading) {
        LoadingDialog()
    }
    if (isError) {
        ErrorQueryDialog(
            showDialog = { hideError() },
            callback = { },
            error = errorMessage
        )
    }
    Scaffold(
        modifier = Modifier.statusBarsPadding().navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(
                modifier = Modifier,
                screen = "Addresses",
                onBackClick = { navigateBack() },
                onContactClick = { navigateToContactUs() }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(horizontal = 25.dp).padding(padding).fillMaxWidth()
                .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            AddProfileButton { navigateToProfile() }
            ProfileList(profileList = list, onDeleteClick = deleteProfile)
        }
    }
}