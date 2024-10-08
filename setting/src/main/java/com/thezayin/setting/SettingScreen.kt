package com.thezayin.setting

import androidx.compose.runtime.Composable
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.setting.components.SettingScreenContent

@Composable
fun SettingScreen(
    navigateUp: () -> Unit,
    navigateToContactUs: () -> Unit
) {
    GlassComponent()
    SetBarColors()
    SettingScreenContent(
        navigateUp = navigateUp,
        navigateToContactUs = navigateToContactUs
    )
}