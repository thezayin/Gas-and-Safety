package com.thezayin.useraddress.presentation.component

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
import ir.kaaveh.sdpcompose.sdp

@Composable
fun AddProfileButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 25.sdp, bottom = 10.sdp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            modifier = Modifier
                .width(90.sdp)
                .height(30.sdp),
            shape = RoundedCornerShape(30.sdp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = com.thezayin.core.R.color.semi_transparent)),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onClick()
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Add New")
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.sdp)
                )
            }
        }
    }
}