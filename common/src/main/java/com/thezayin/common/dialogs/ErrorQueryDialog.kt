package com.thezayin.common.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ErrorQueryDialog(showDialog: (Boolean) -> Unit, callback: () -> Unit, error: String) {
    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(16.sdp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.sdp)) {

                    Spacer(modifier = Modifier.height(20.sdp))
                    Text(
                        text = error,
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular)),
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.sdp))
                    Button(
                        onClick = {
                            showDialog(false)
                            callback()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(6.sdp),
                        colors = ButtonDefaults.buttonColors(contentColor = colorResource(id = com.thezayin.core.R.color.black))
                    ) {
                        Text(
                            text = "Done",
                            fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular)),
                            color = colorResource(id = com.thezayin.core.R.color.white),
                            fontSize = 12.ssp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}