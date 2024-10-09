package com.thezayin.splash.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ImageHeader(modifier: Modifier) {
    Card(
        modifier = modifier.size(120.sdp),
        shape = RoundedCornerShape(120.sdp)
    ) {
        Image(
            painter = painterResource(id = com.thezayin.assets.R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview
@Composable
fun ImageHeaderPreview() {
    ImageHeader(Modifier)
}