package com.thezayin.presentation.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp

@Preview
@Composable
fun ProductImage(
    modifier: Modifier = Modifier,
    productImage: Uri? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(15.sdp).fillMaxWidth().height(200.sdp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.semi_transparent)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = productImage,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(R.drawable.ic_connection),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Fit,
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.sdp)
        ) {
            Spacer(
                modifier = Modifier.clip(shape = RoundedCornerShape(5.sdp))
                    .background(color = colorResource(R.color.semi_transparent)).width(20.sdp)
                    .height(30.sdp)
            )
            Spacer(
                modifier = Modifier.clip(shape = RoundedCornerShape(5.sdp))
                    .background(color = colorResource(R.color.semi_transparent)).width(20.sdp)
                    .height(30.sdp)
            )
            Spacer(
                modifier = Modifier.clip(shape = RoundedCornerShape(5.sdp))
                    .background(color = colorResource(R.color.semi_transparent)).width(20.sdp)
                    .height(30.sdp)
            )
        }
    }
}