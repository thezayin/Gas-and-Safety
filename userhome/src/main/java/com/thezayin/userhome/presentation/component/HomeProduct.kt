package com.thezayin.userhome.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.thezayin.core.R
import com.thezayin.entities.HomeProductsModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun HomeProduct(
    product: HomeProductsModel?,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(top = 5.sdp, bottom = 5.sdp)
            .clip(shape = RoundedCornerShape(8.sdp))
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.sdp, max = 400.sdp)
                .padding(horizontal = 15.sdp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 10.sdp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    model = product?.imageUri ?: R.drawable.ic_mail,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.sdp))
                        .size(50.sdp)
                        .background(color = Color.White),
                    contentScale = ContentScale.Fit
                )
                Column(
                    modifier = Modifier.padding(start = 10.sdp)
                ) {
                    Text(
                        text = product?.name ?: "Product Name",
                        color = colorResource(id = R.color.black),
                        fontSize = 10.ssp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    )
                    Spacer(modifier = Modifier.height(5.sdp))
                    Text(
                        text = product?.description
                            ?: "Composite Fiber LPG Cylinder of BGC (Burhan Gas Company) Carries 10 Kg Gas",
                        color = colorResource(id = R.color.black),
                        fontSize = 8.ssp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(start = 5.sdp),
                    text = " Rs: ${product?.price}",
                    color = colorResource(id = R.color.black),
                    fontSize = 14.ssp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                )
                Card(
                    modifier = Modifier
                        .padding(bottom = 10.sdp, top = 5.sdp)
                        .width(100.sdp)
                        .height(30.sdp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.black)
                    ),
                    shape = RoundedCornerShape(5.sdp),
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 5.sdp)
                            .fillMaxSize()
                            .clickable {
                                onClick()
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Add to Cart", color = colorResource(id = R.color.white))
                        Image(
                            painter = painterResource(id = R.drawable.ic_cart),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = colorResource(id = R.color.white)),
                            modifier = Modifier.size(12.sdp)
                        )
                    }
                }
            }
        }

    }
}

@Composable
@Preview
fun HomeProductPreview() {
    HomeProduct(null)
}