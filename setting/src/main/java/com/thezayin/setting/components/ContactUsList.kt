package com.thezayin.setting.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.thezayin.framework.extension.makeCall
import com.thezayin.framework.extension.openLink
import com.thezayin.framework.extension.sendMail
import com.thezayin.framework.extension.sendWhatsappMsg
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
@Preview
fun ContactUsList() {
    val activity = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(shape = RoundedCornerShape(8.sdp), colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.semi_transparent)
        ), onClick = {
            activity.makeCall()
        }) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_call),
                    contentDescription = null,
                    modifier = Modifier.size(15.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "03366511222",
                    color = colorResource(id = R.color.black),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier.padding(vertical = 18.sdp).padding(start = 15.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier.fillMaxWidth().height(8.sdp)
        )
        Card(shape = RoundedCornerShape(8.sdp), colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.semi_transparent)
        ), onClick = {
            activity.sendWhatsappMsg()
        }) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_whatsapp),
                    contentDescription = null,
                    modifier = Modifier.size(12.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "03366511222",
                    color = colorResource(id = R.color.black),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier.padding(vertical = 18.sdp).padding(start = 15.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier.fillMaxWidth().height(8.sdp)
        )

        Card(shape = RoundedCornerShape(8.sdp), colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.semi_transparent)
        ), onClick = {
            activity.sendMail()
        }) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mail),
                    contentDescription = null,
                    modifier = Modifier.size(10.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "gasandsafety@gmail.com",
                    color = colorResource(id = R.color.black),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier.padding(vertical = 18.sdp).padding(start = 15.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier.fillMaxWidth().height(8.sdp)
        )

        Card(shape = RoundedCornerShape(8.sdp), colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.semi_transparent)
        ), onClick = {
            activity.openLink("https://www.facebook.com/profile.php?id=61561593933149&mibextid=ZbWKwL")
        }) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = null,
                    modifier = Modifier.size(10.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "like us on facebook",
                    color = colorResource(id = R.color.black),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier.padding(vertical = 18.sdp).padding(start = 15.sdp)
                )
            }
        }
        Spacer(
            modifier = Modifier.fillMaxWidth().height(8.sdp)
        )
        Card(shape = RoundedCornerShape(8.sdp), colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.semi_transparent)
        ), onClick = {
            activity.openLink("https://www.instagram.com/gasandsafety?igsh=MXM1M3YybDJiZTAwdg==")
        }) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_instagram),
                    contentDescription = null,
                    modifier = Modifier.size(10.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "follow us on instagram",
                    color = colorResource(id = R.color.black),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier.padding(vertical = 18.sdp).padding(start = 15.sdp)
                )
            }
        }


        Spacer(
            modifier = Modifier.fillMaxWidth().height(8.sdp)
        )
    }
}