package com.thezayin.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun SaveProfileButton(
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(bottom = 20.sdp)
            .fillMaxWidth()
            .height(40.sdp)
            .padding(horizontal = 20.sdp),
        shape = RoundedCornerShape(10.sdp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = com.thezayin.assets.R.color.black),
        )
    ) {
        Text(
            text = "Save",
            color = colorResource(id = com.thezayin.assets.R.color.white),
            fontSize = 12.ssp,
            fontFamily = FontFamily(Font(com.thezayin.assets.R.font.noto_sans_bold)),
        )
    }
}

@Preview
@Composable
fun SaveProfileButtonPreview() {
    SaveProfileButton {}
}