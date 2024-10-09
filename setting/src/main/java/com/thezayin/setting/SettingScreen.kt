package com.thezayin.setting

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.setting.components.SettingScreenContent

@Composable
fun SettingScreen(
    navigateUp: () -> Unit,
    navigateToContactUs: () -> Unit
) {
    BackHandler(onBack = navigateUp)
    GlassComponent()
    SetBarColors()
    SettingScreenContent(
        navigateUp = navigateUp,
        navigateToContactUs = navigateToContactUs
    )
}