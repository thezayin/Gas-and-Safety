package com.thezayin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import coil.compose.AsyncImage
import com.thezayin.assets.R
import com.thezayin.databases.model.CartModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * CartProductInfo displays the information of a product in the cart,
 * including its image, name, description, and quantity.
 *
 * @param product The [CartModel] instance representing the product details.
 * @param onIncrement Callback function invoked when the increment button is clicked.
 * @param onDecrement Callback function invoked when the decrement button is clicked.
 */
@Composable
fun CartProductInfo(
    product: CartModel?,
    onIncrement: (CartModel) -> Unit = {},
    onDecrement: (CartModel) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(top = 5.sdp, bottom = 5.sdp)
            .clip(shape = RoundedCornerShape(10.sdp))
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.sdp)
                .fillMaxWidth()
                .heightIn(min = 90.sdp, max = 400.sdp),
            horizontalAlignment = Alignment.End
        ) {
            // Product details section
            Row(
                modifier = Modifier
                    .padding(top = 15.sdp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    model = product?.imageUri ?: R.drawable.ic_mail,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(100.sdp))
                        .size(40.sdp)
                        .background(color = Color.White),
                    contentScale = ContentScale.Fit
                )
                Column(
                    modifier = Modifier.padding(start = 20.sdp, end = 10.sdp)
                ) {
                    Text(
                        text = product?.name ?: "Product Name",
                        color = colorResource(id = R.color.black),
                        fontSize = 10.ssp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold))
                    )
                    Text(
                        text = product?.description ?: "Product Description",
                        color = colorResource(id = R.color.black),
                        fontSize = 8.ssp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_regular))
                    )
                }
            }
            // Quantity control section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.sdp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_minus),
                        contentDescription = null,
                        modifier = Modifier
                            .size(12.sdp)
                            .clickable {
                                product?.let { onDecrement(it) }
                            }
                    )
                    Text(
                        text = " ${product?.quantity} ",
                        color = colorResource(id = R.color.black),
                        fontSize = 10.ssp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold))
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = null,
                        modifier = Modifier
                            .size(12.sdp)
                            .clickable {
                                product?.let { onIncrement(it) }
                            }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun CartProductInfoPreview() {
    CartProductInfo(product = null)
}