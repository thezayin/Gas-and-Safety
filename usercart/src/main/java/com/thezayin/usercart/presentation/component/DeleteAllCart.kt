package com.thezayin.usercart.presentation.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R

@Composable
fun DeleteAllCart(callback: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .padding(horizontal = 25.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            modifier = Modifier
                .height(40.dp)
                .width(100.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent),
            )
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        callback()
                    }
                    .padding(horizontal = 10.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Empty",
                    color = colorResource(id = R.color.black),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(20.dp)
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