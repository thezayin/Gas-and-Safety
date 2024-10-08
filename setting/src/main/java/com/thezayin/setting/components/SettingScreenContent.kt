package com.thezayin.setting.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.assets.R
import com.thezayin.common.component.UserTopBar
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SettingScreenContent(
    navigateUp: () -> Unit,
    navigateToContactUs: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(
                modifier = Modifier,
                screen = "Setting",
                onBackClick = { navigateUp() },
                onContactClick = { navigateToContactUs() }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 30.sdp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) { SettingOptionsList() }
    }
}