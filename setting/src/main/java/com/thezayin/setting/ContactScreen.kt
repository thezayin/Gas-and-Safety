package com.thezayin.setting

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
    ContactScreenContact(
        navigateUp = navigateUp
    )
}