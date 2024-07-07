package com.thezayin.lpg.presentation.users.cart.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.thezayin.lpg.presentation.users.cart.CartViewModel

@Composable
fun CartProductInfo(
    product: com.thezayin.entities.CartModel?,
    cartViewModel: CartViewModel?
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
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(130.dp),
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
                        .size(60.dp)
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
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Row {
                    Image(
                        painter = painterResource(id = com.thezayin.core.R.drawable.ic_minus),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                cartViewModel?.decrementQuantity(product!!)
                            }
                    )
                    Text(
                        text = " ${product?.quantity} ",
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                    )
                    Image(
                        painter = painterResource(id = com.thezayin.core.R.drawable.ic_plus),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                cartViewModel?.incrementQuantity(product!!)
                            })
                }
            }
        }
    }
}

@Composable
@Preview
fun CartProductInfoPreview() {
    CartProductInfo(product = null, cartViewModel = null)
}