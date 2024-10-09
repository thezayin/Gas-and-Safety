package com.thezayin.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.thezayin.assets.R
import com.thezayin.domain.model.OrderModel
import com.thezayin.framework.extension.copyToClipboard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable that displays an order history card. The card includes order details such as
 * order ID, name, phone number, address, total amount, and the list of products ordered.
 *
 * @param order The OrderModel object that contains the order details to be displayed.
 */
@Composable
fun HistoryCard(
    order: OrderModel?
) {
    // Get the current context to handle clipboard functionality.
    val context = LocalContext.current

    // Outer Box that defines padding, background color, and shape of the card.
    Box(
        modifier = Modifier.padding(top = 5.sdp, bottom = 5.sdp)
            .clip(shape = RoundedCornerShape(8.sdp))
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxWidth()
    ) {
        // Column to arrange the elements vertically within the card.
        Column(
            modifier = Modifier.heightIn(min = 250.sdp, max = 700.sdp)
                .fillMaxWidth()
                .padding(horizontal = 10.sdp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            // Header section displaying order time, date, and status.
            Row(
                modifier = Modifier.padding(top = 20.sdp, bottom = 5.sdp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    modifier = Modifier.padding(start = 10.sdp),
                    verticalArrangement = Arrangement.spacedBy(5.sdp)
                ) {
                    // Row for displaying order time, date, and status.
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${order?.orderTime} ${order?.orderDate}",
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
                            // Display order status, defaulting to "pending" if not available.
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

                    // Row to display and copy the order ID to clipboard.
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
                            modifier = Modifier.size(15.sdp).clickable {
                                // Copy order ID to clipboard on click.
                                context.copyToClipboard(order?.id ?: "000000")
                            }
                        )
                    }

                    // Display the customer's name, phone number, and address.
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

                    // Section to display the list of ordered products.
                    Text(
                        text = "Orders",
                        color = colorResource(id = R.color.black),
                        fontSize = 12.ssp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(R.font.noto_sans_italic)),
                    )
                    // Display the products list using OrderProductList composable.
                    order?.orders?.let { OrderProductList(orders = it) }
                }
            }

            // Footer section displaying the total amount of the order.
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

/**
 * A preview composable to showcase how the HistoryCard would look.
 */
@Composable
@Preview
fun PreviewHistoryCard() {
    HistoryCard(order = null)
}