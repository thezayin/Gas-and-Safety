package com.thezayin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun DeleteAllCart(emptyClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 10.sdp)
            .padding(horizontal = 10.sdp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            modifier = Modifier
                .height(30.sdp)
                .width(60.sdp),
            shape = RoundedCornerShape(10.sdp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent),
            )
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        emptyClick()
                    }
                    .padding(horizontal = 5.sdp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Empty",
                    color = colorResource(id = R.color.black),
                    fontSize = 8.ssp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 2.sdp)
                        .size(10.sdp)
                )
            }
        }
    }
}

@Composable
@Preview
fun DeleteAllCartPreview() {
    DeleteAllCart {}
}