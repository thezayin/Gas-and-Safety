package com.thezayin.lpg.presentation.users.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.lpg.R
import com.thezayin.lpg.common.component.GlassComponent
import com.thezayin.lpg.common.component.UserTopBar
import com.thezayin.lpg.presentation.users.setting.component.SettingOptionsList

@Destination
@Composable
fun SettingScreen(
    navigator: DestinationsNavigator
) {
    GlassComponent()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(modifier = Modifier, navigator = navigator, screen = "Settings")
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 60.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            SettingOptionsList()
        }
    }
}