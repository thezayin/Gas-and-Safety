package com.thezayin.userorderhistory.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R
import com.thezayin.entities.OrderModel

@Composable
fun HistoryCard(
    order: OrderModel?
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
                .heightIn(min = 250.dp, max = 700.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = order?.orderTime + " " + order?.orderDate,
                            color = colorResource(id = R.color.black),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                        )
                        Card(
                            modifier = Modifier.padding(bottom = 10.dp),
                            shape = RoundedCornerShape(40.dp),
                            colors = CardDefaults.cardColors(
                                colorResource(id = R.color.black)
                            )
                        ) {
                            Text(
                                text = order?.orderStatus ?: "pending",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                                color = colorResource(id = R.color.white),
                                modifier = Modifier.padding(
                                    vertical = 4.dp, horizontal = 12.dp
                                )
                            )
                        }
                    }

                    Text(
                        text = order?.name ?: "name",
                        color = colorResource(id = R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    )


                    Text(
                        text = order?.phoneNumber ?: "03011001111",
                        color = colorResource(id = R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    )

                    Text(
                        text = order?.address ?: "Islamabad",
                        color = colorResource(id = R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    )
                    Text(
                        text = "Orders",
                        color = colorResource(id = R.color.black),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(R.font.noto_sans_italic)),
                    )
                    OrderProductList(orders = order!!.orders!!)
                }
            }
            Row(
                modifier = Modifier.padding(vertical = 25.dp),
            ) {
                Text(
                    text = "Total: ${order?.totalAmount ?: "0"}Rs.",
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewHistoryCard() {
    HistoryCard(order = null)
}