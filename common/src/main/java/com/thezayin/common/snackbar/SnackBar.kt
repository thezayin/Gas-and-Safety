package com.thezayin.common.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun RememberSnackBar(
    cartTintColor: Int,
    message: String = "Added",
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    callback: (Boolean) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        scope.launch {
            delay(2000L)
            callback(false)
        }
    }
    Column(
        modifier = Modifier
            .background(
                color = colorResource(id = cartTintColor)
            ),
    ) {
        Snackbar(
            modifier = modifier.height(100.sdp),
            containerColor = colorResource(id = cartTintColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.sdp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = message,
                    fontSize = 16.ssp,
                    color = colorResource(id = com.thezayin.core.R.color.white),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular))
                )
            }
        }
    }
}