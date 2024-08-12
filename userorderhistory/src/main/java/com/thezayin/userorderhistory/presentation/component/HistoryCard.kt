package com.thezayin.userorderhistory.presentation.component

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thezayin.core.R
import com.thezayin.entities.OrderModel
import com.thezayin.framework.extension.functions.copyToClipboard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun HistoryCard(
    order: OrderModel?
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(top = 5.sdp, bottom = 5.sdp)
            .clip(shape = RoundedCornerShape(8.sdp))
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .heightIn(min = 250.sdp, max = 700.sdp)
                .fillMaxWidth()
                .padding(horizontal = 10.sdp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 20.sdp, bottom = 5.sdp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    modifier = Modifier.padding(start = 10.sdp),
                    verticalArrangement = Arrangement.spacedBy(5.sdp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = order?.orderTime + " " + order?.orderDate,
                            color = colorResource(id = R.color.black),
                            fontSize = 10.ssp,
                            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                        )
                        Card(
                            modifier = Modifier.padding(bottom = 10.sdp),
                            shape = RoundedCornerShape(40.sdp),
                            colors = CardDefaults.cardColors(
                                colorResource(id = R.color.black)
                            )
                        ) {
                            Text(
                                text = order?.orderStatus ?: "pending",
                                fontSize = 8.ssp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                                color = colorResource(id = R.color.white),
                                modifier = Modifier.padding(
                                    vertical = 4.sdp, horizontal = 12.sdp
                                )
                            )
                        }
                    }

                    Row {
                        Text(
                            text = "Order Id:",
                            modifier = Modifier.padding(end = 5.sdp),
                            color = colorResource(id = R.color.black),
                            fontSize = 10.ssp,
                            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                        )
                        Text(
                            text = order?.id ?: "000000",
                            color = colorResource(id = R.color.black),
                            fontSize = 10.ssp,
                            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_copy),
                            contentDescription = null,
                            modifier = Modifier
                                .size(15.sdp)
                                .clickable {
                                    context.copyToClipboard(order?.id ?: "000000")
                                }
                        )
                    }

                    Text(
                        text = order?.name ?: "name",
                        color = colorResource(id = R.color.black),
                        fontSize = 12.ssp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    )


                    Text(
                        text = order?.phoneNumber ?: "03011001111",
                        color = colorResource(id = R.color.black),
                        fontSize = 12.ssp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    )

                    Text(
                        text = order?.address ?: "Islamabad",
                        color = colorResource(id = R.color.black),
                        fontSize = 12.ssp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    )
                    Text(
                        text = "Orders",
                        color = colorResource(id = R.color.black),
                        fontSize = 12.ssp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(R.font.noto_sans_italic)),
                    )
                    OrderProductList(orders = order!!.orders!!)
                }
            }
            Row(
                modifier = Modifier.padding(vertical = 15.sdp),
            ) {
                Text(
                    text = "Total: ${order?.totalAmount ?: "0"}Rs.",
                    color = colorResource(id = R.color.black),
                    fontSize = 12.ssp,
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