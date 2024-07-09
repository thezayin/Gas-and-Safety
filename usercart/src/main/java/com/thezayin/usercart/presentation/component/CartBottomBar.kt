package com.thezayin.usercart.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thezayin.core.R

@Composable
fun CartBottomBar(
    modifier: Modifier,
    onClick: (String) -> Unit = {},
    price: String
) {
    Column(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                )
            )
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxWidth()

    ) {
        TotalPrice(price = price)
        CartBuyButton(
            modifier = Modifier,
        ) {
            onClick(price)
        }
    }
}

@Composable
@Preview
fun CartBottomBarPreview() {
    CartBottomBar(modifier = Modifier, onClick = {}, price = "10000")
}