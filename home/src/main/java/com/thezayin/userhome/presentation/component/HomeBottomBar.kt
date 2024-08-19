package com.thezayin.userhome.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.core.R
import com.thezayin.entities.ProfileModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun HomeBottomBar(
    showBadge: Boolean = false,
    modifier: Modifier,
    badgeText: String,
    profileList: List<ProfileModel>,
    navigateToHistory: () -> Unit = {},
    navigateToCart: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
    navigateToAddress: () -> Unit = {}
) {

    Row(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topEnd = 20.sdp,
                    topStart = 20.sdp
                )
            )
            .background(color = colorResource(id = R.color.semi_transparent))
            .padding(bottom = 10.sdp, top = 10.sdp)
            .fillMaxWidth()
            .height(50.sdp)
            .padding(horizontal = 25.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier
                .width(80.sdp)
                .height(40.sdp),
            shape = RoundedCornerShape(25.sdp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.black)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(20.sdp),
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = null,
                )
                Text(
                    text = "Home",
                    fontSize = 10.ssp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(start = 5.sdp)
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_history),
            contentDescription = null,
            modifier = Modifier
                .size(20.sdp)
                .clickable {
                    navigateToHistory()
                }
        )

        BadgedBox(badge = {
            if (showBadge) {
                Badge { Text(text = badgeText) }
            }
        }, modifier = Modifier
            .size(30.sdp)
            .clickable {
                navigateToCart()
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cart),
                contentDescription = null,
                modifier = Modifier
                    .size(25.sdp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = null,
            modifier = Modifier
                .size(22.sdp)
                .clickable {
                    if (profileList.isEmpty()) {
                        navigateToProfile()
                    } else {
                        navigateToAddress()
                    }
                }
        )
    }
}

@Composable
@Preview
fun HomeBottomBarPreview() {
    HomeBottomBar(true, Modifier, "10", emptyList())
}