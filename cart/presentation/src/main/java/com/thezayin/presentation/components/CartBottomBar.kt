package com.thezayin.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun CartBottomBar(
    modifier: Modifier,
    onBuyClick: (String) -> Unit = {},
    price: String
) {
    Column(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = 20.sdp,
                    topEnd = 20.sdp
                )
            )
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxWidth()

    ) {
        TotalPrice(price = price)
        CartBuyButton(
            modifier = Modifier,
        ) {
            onBuyClick(price)
        }
    }
}

@Composable
@Preview
fun CartBottomBarPreview() {
    CartBottomBar(modifier = Modifier, onBuyClick = {}, price = "10000")
}