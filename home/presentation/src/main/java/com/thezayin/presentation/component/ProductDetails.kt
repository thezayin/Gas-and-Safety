package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ProductDetails(
    modifier: Modifier,
    productName: String,
    productDescription: String,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(15.sdp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = productName,
                textAlign = TextAlign.Center,
                fontSize = 20.ssp,
                fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                color = colorResource(R.color.black)
            )
        }
        Spacer(modifier = Modifier.padding(5.sdp))
        Text(
            text = productDescription,
            textAlign = TextAlign.Start,
            fontSize = 12.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
            color = colorResource(R.color.black)
        )
    }
}