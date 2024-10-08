package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun TotalPrice(price: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.sdp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Total Price: $price",
            modifier = Modifier.padding(top = 15.sdp, bottom = 15.sdp),
            fontSize = 18.ssp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(Font(com.thezayin.assets.R.font.noto_sans_bold))
        )
    }
}

@Composable
@Preview
fun TotalPricePreview() {
    TotalPrice(price = "1000")
}