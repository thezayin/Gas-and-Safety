package com.thezayin.common.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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

@Composable
fun StoreClosedDialog(
    onDismiss: () -> Unit,
    showDialog: (Boolean) -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
    ){
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Store Closed",
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "The store is currently closed. You can still place an order, but it will be processed when the store opens.",
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular)),
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { showDialog(false) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(contentColor = colorResource(id = com.thezayin.core.R.color.black))
                    ) {
                        Text(
                            text = "OK",
                            fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_regular)),
                            color = colorResource(id = com.thezayin.core.R.color.white),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}