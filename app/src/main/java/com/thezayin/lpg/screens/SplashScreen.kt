package com.thezayin.lpg.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.lpg.R
import com.thezayin.lpg.screens.destinations.HomeScreenDestination
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.coroutines.delay

@SuppressLint("OpaqueUnitKey")
@Composable
@RootNavGraph(start = true)
@Destination
fun SplashScreen(navController: DestinationsNavigator) {
    GlassComponent()
    SetBarColors()
    LaunchedEffect(Unit) {
        delay(timeMillis = 5000L)
        navController.navigate(HomeScreenDestination)
    }

    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(180.sdp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 15.sdp, vertical = 15.sdp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 10.sdp),
                color = colorResource(id = R.color.black),
                text = "PROVIDING SAFE GAS ENERGY AND SECURING YOUR WORLD BY PROVIDING TRAINING AND SAFETY EQUIPMENT.",
                fontSize = 8.ssp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular))
            )
        }
    }
}