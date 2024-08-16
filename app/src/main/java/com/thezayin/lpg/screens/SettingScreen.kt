package com.thezayin.lpg.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.SetBarColors
import com.thezayin.common.component.UserTopBar
import com.thezayin.framework.extension.functions.openLink
import com.thezayin.framework.extension.functions.sendMail
import com.thezayin.framework.utils.Constants
import com.thezayin.framework.utils.Constants.ABOUT_US_URL
import com.thezayin.framework.utils.Constants.TERMS_CONDITIONS_URL
import com.thezayin.lpg.R
import com.thezayin.lpg.screens.destinations.ContactUsScreenDestination
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Destination
@Composable
fun SettingScreen(
    navigator: DestinationsNavigator
) {
    GlassComponent()
    SetBarColors()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(
                modifier = Modifier,
                screen = "Setting",
                onBackClick = { navigator.navigateUp() },
                onContactClick = { navigator.navigate(ContactUsScreenDestination) }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 30.sdp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) { SettingOptionsList() }
    }
}

@Preview
@Composable
fun OtherListComponent() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.sdp)
            .padding(horizontal = 15.sdp)
    ) {
        Text(
            text = "Others",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.sdp),
            fontSize = 12.ssp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
            color = colorResource(id = R.color.black),
        )

        Card(
            shape = RoundedCornerShape(10.sdp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {}
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "Leave a rating/review",
                    color = colorResource(id = R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier
                        .padding(vertical = 15.sdp)
                        .padding(start = 10.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.sdp)
        )

        Card(
            shape = RoundedCornerShape(10.sdp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {
                context.openLink(ABOUT_US_URL)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "About Us",
                    color = colorResource(id = R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier
                        .padding(vertical = 15.sdp)
                        .padding(start = 10.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.sdp)
        )
    }
}

@Preview
@Composable
fun SettingHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(80.sdp),
            shape = RoundedCornerShape(80.sdp),
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "Gas and Safety",
            fontSize = 16.ssp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_medium)),
            color = colorResource(id = com.thezayin.core.R.color.black),
            modifier = Modifier.padding(top = 10.sdp)
        )
    }
}

@Preview
@Composable
fun LegalListComponent() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.sdp, bottom = 10.sdp)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Legal",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.sdp),
            fontSize = 12.ssp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
            color = colorResource(id = com.thezayin.core.R.color.black),
        )
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {
                context.openLink(Constants.PRIVATE_POLICY_URL)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_privacy),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "Privacy Policy",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier
                        .padding(vertical = 15.sdp)
                        .padding(start = 10.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.sdp)
        )

        Card(
            shape = RoundedCornerShape(10.sdp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {
                context.openLink(TERMS_CONDITIONS_URL)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_terms),
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "Terms & Conditions",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier
                        .padding(vertical = 15.sdp)
                        .padding(start = 10.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.sdp)
        )

        Card(
            shape = RoundedCornerShape(10.sdp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {
                context.sendMail()
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_mail),
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.sdp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "Contact Us",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 10.ssp,
                    modifier = Modifier
                        .padding(vertical = 15.sdp)
                        .padding(start = 10.sdp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp)
        )
    }
}

@Preview
@Composable
fun SettingOptionsList() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SettingHeader()
        OtherListComponent()
        LegalListComponent()
    }
}