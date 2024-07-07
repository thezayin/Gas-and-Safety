package com.thezayin.lpg.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.lpg.destinations.ContactUsScreenDestination

@Composable
fun UserTopBar(
    modifier: Modifier,
    screen: String,
    navigator: DestinationsNavigator,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 60.dp)
            .padding(horizontal = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = com.thezayin.core.R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    navigator.navigateUp()
                }
                .size(25.dp),
        )
        Text(
            text = screen,
            color = colorResource(id = com.thezayin.core.R.color.black),
            fontSize = 17.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
        )
        Image(
            painter = painterResource(id = com.thezayin.core.R.drawable.ic_call),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    navigator.navigate(ContactUsScreenDestination)
                }
                .size(30.dp),
        )
    }
}