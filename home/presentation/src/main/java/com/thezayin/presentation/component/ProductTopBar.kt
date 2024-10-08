package com.thezayin.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ProductTopBar(
    modifier: Modifier,
    screen: String,
    onBackClick: () -> Unit,
    onContactClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(top = 20.sdp).padding(horizontal = 15.sdp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier.clickable {
                onBackClick()
            }.size(20.sdp),
        )
        Text(
            text = screen,
            color = colorResource(id = R.color.black),
            fontSize = 12.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
        )
        Image(
            painter = painterResource(id = R.drawable.ic_call),
            contentDescription = null,
            modifier = Modifier.size(20.sdp).clickable {
                onContactClick()
            },
        )
    }
}