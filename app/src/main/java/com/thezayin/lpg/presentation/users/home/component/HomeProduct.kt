package com.thezayin.lpg.presentation.users.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thezayin.lpg.R
import com.thezayin.entities.HomeProductsModel

@Composable
fun HomeProduct(
    product: com.thezayin.entities.HomeProductsModel?,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    model = product?.imageUri ?: com.thezayin.core.R.drawable.ic_mail,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.dp))
                        .size(50.dp)
                        .background(color = Color.White),
                    contentScale = ContentScale.Fit
                )
                Column(
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Text(
                        text = product?.name ?: "Product Name",
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                    )
                    Text(
                        text = product?.description ?: "Product Description",
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = " Rs: ${product?.price}",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                )
            }
            Card(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .width(80.dp)
                    .height(40.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.black)
                ),
                shape = RoundedCornerShape(10.dp),
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxSize()
                        .clickable {
                            onClick()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Buy", color = colorResource(id = R.color.white))
                    Image(
                        painter = painterResource(id = com.thezayin.core.R.drawable.ic_cart),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = colorResource(id = R.color.white)),
                        modifier = Modifier.size(20.dp)
                    )
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