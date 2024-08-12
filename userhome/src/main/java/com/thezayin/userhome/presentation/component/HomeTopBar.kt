package com.thezayin.userhome.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ir.kaaveh.sdpcompose.sdp

@Composable
fun TopBarComponent(
    modifier: Modifier,
    navigateToSetting: () -> Unit = {},
    navigateToContact: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.sdp)
            .padding(horizontal = 15.sdp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = com.thezayin.core.R.drawable.ic_menue),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    navigateToSetting()
                }
                .size(20.sdp),
        )

        Image(
            painter = painterResource(id = com.thezayin.core.R.drawable.ic_call),
            contentDescription = null,
            modifier = Modifier
                .size(25.sdp)
                .clickable {
                    navigateToContact()
                },
        )
    }
}

@Composable
@Preview
fun TopBarComponentPreview() {
    TopBarComponent(modifier = Modifier)
}