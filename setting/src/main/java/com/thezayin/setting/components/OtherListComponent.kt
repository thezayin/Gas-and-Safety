package com.thezayin.setting.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.assets.R
import com.thezayin.framework.extension.openLink
import com.thezayin.framework.utils.Constants.ABOUT_US_URL
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun OtherListComponent() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.sdp)
            .padding(horizontal = 15.sdp)
    ) {
        Text(
            text = "Others",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.sdp),
            fontSize = 12.ssp,
            fontFamily = FontFamily(Font(com.thezayin.assets.R.font.noto_sans_regular)),
            color = colorResource(id = R.color.black),
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.sdp)
        )

        Card(
            shape = RoundedCornerShape(10.sdp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {
                context.openLink(ABOUT_US_URL)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.assets.R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "About Us",
                    color = colorResource(id = R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.assets.R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier
                        .padding(vertical = 15.sdp)
                        .padding(start = 10.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.sdp)
        )
    }
}