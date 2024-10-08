package com.thezayin.splash

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.assets.R
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.framework.extension.checkForInternet
import com.thezayin.splash.components.BottomText
import com.thezayin.splash.components.ImageHeader
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    val viewModel: SplashViewModel = koinInject()
    val activity = LocalContext.current as Activity

    val checkInternetConnection by remember { mutableStateOf(activity.checkForInternet()) }

    val showInternetError = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (checkInternetConnection) {
            delay(10000)
            onSplashFinished()
        } else {
            showInternetError.value = true
        }
    }

    if (showInternetError.value) {
        NetworkDialog(
            showDialog = {
                activity.finish()
            }
        )
    }

    SetBarColors()
    GlassComponent()
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        bottomBar = {
            Column {
                BottomText(modifier = Modifier)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ImageHeader(modifier = Modifier.align(Alignment.Center))
        }
    }
}