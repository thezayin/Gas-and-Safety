package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.thezayin.databases.model.CartModel
import ir.kaaveh.sdpcompose.ssp

@Composable
fun OrderProductList(
    orders: List<CartModel>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(orders.size) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = orders[it].name + " x " + orders[it].quantity.toString(),
                    color = colorResource(id = com.thezayin.assets.R.color.black),
                    fontSize = 8.ssp,
                    fontFamily = FontFamily(Font(com.thezayin.assets.R.font.noto_sans_bold)),
                )

                Text(
                    text = "Rs. ${orders[it].price ?: "0"}",
                    fontSize = 8.ssp,
                    fontFamily = FontFamily(Font(com.thezayin.assets.R.font.noto_sans_bold)),
                    color = colorResource(id = com.thezayin.assets.R.color.black)
                )
            }
        }
    }
}
