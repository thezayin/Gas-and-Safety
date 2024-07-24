package com.thezayin.lpg.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.lpg.R
import com.thezayin.lpg.screens.destinations.HomeScreenDestination
import kotlinx.coroutines.delay

@SuppressLint("OpaqueUnitKey")
@Composable
@RootNavGraph(start = true)
@Destination
fun SplashScreen(navController: DestinationsNavigator) {
    GlassComponent()
    LaunchedEffect(Unit) {
        delay(timeMillis = 5000L)
        navController.navigate(HomeScreenDestination)
    }

    Box(
        modifier = Modifier
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(180.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 25.dp, vertical = 25.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                color = colorResource(id = R.color.black),
                text = "Order LPG cylinders safely and conveniently with Gass and Safety.",
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular))
            )
        }
    }
}