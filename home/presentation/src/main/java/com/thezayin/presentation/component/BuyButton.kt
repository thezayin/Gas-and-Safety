package com.thezayin.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun BuyButton(
    modifier: Modifier = Modifier,
    price: String = "1000",
    onBuyClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 15.sdp, vertical = 20.sdp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(40.sdp)
    ) {
        Text(
            text = "$price RS",
            fontSize = 22.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
            color = colorResource(R.color.black)
        )
        Button(
            onClick = { onBuyClick() },
            modifier = Modifier.padding(start = 10.sdp).fillMaxWidth().height(50.sdp),
            shape = RoundedCornerShape(20.sdp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.black),
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Buy",
                    fontSize = 16.ssp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    color = colorResource(R.color.white)
                )
                Image(
                    modifier = Modifier.size(25.sdp),
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.white)),
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = "cart"
                )
            }
        }
    }
}