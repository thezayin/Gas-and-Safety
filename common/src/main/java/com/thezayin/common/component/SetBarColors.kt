package com.thezayin.common.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.res.colorResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thezayin.core.R

@Composable
fun SetBarColors() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val statusBarLight = colorResource(id = R.color.semi_transparent)
    val statusBarDark = colorResource(id = R.color.semi_transparent)
    val navigationBarLight = colorResource(id = R.color.semi_transparent)
    val navigationBarDark = colorResource(id = R.color.semi_transparent)

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setNavigationBarColor(
            color = if (useDarkIcons) {
                statusBarLight
            } else {
                statusBarDark
            },
            darkIcons = useDarkIcons
        )
        systemUiController.setStatusBarColor(
            color = if (useDarkIcons) {
                navigationBarLight
            } else {
                navigationBarDark
            },
            darkIcons = useDarkIcons
        )

        onDispose { }
    }
}