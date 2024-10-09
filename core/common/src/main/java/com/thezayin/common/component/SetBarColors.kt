package com.thezayin.common.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import com.thezayin.assets.R

@Composable
fun SetBarColors() {
    val view = LocalView.current
    val context = LocalContext.current

    // Set status bar color
    val statusBarColor = colorResource(R.color.white) // Semi-transparent color
    view.setSystemUiVisibility(0) // Clear previous flags
    view.setBackgroundColor(statusBarColor.toArgb())

    // Set navigation bar color
    val navigationBarColor = colorResource(R.color.white) // Semi-transparent color
    view.setSystemUiVisibility(0)
    view.setBackgroundColor(navigationBarColor.toArgb())
}