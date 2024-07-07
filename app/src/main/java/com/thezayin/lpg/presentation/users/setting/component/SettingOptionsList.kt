package com.thezayin.lpg.presentation.users.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SettingOptionsList() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SettingHeader()
        OtherListComponent()
        LegalListComponent()
    }
}