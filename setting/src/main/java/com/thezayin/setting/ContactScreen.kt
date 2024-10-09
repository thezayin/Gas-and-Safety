package com.thezayin.setting

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.setting.components.ContactScreenContact

@Composable
fun ContactScreen(
    navigateUp: () -> Unit
) {
    GlassComponent()
    SetBarColors()
    BackHandler(onBack = navigateUp)
    ContactScreenContact(
        navigateUp = navigateUp
    )
}