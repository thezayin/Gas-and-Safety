package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
@Preview
fun NoProductFound() {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.sdp)
            .padding(top = 100.sdp)
            .fillMaxWidth()
            .height(150.sdp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.semi_transparent)
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "No Products in Cart",
                fontSize = 15.ssp,
                fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                color = colorResource(id = R.color.black)
            )
        }
    }
}