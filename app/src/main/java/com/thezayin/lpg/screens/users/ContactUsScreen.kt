package com.thezayin.lpg.screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.framework.extension.functions.makeCall
import com.thezayin.framework.extension.functions.sendMail
import com.thezayin.lpg.R
import com.thezayin.common.component.GlassComponent
import com.thezayin.common.component.UserTopBar
import com.thezayin.lpg.destinations.ContactUsScreenDestination

@Composable
@Destination
fun ContactUsScreen(
    navigator: DestinationsNavigator
) {

    com.thezayin.common.component.GlassComponent()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.semi_transparent),
        topBar = {
            UserTopBar(
                modifier = Modifier,
                screen = "Profile",
                onBackClick = { navigator.navigateUp() },
                onContactClick = {navigator.navigate(ContactUsScreenDestination)}
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 60.dp)
                .padding(horizontal = 25.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            ContactUsList()
        }
    }
}

@Composable
@Preview
fun ContactUsList() {
    val activity = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {
                activity.makeCall()
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_call),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "03122345671",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_whatsapp),
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "03121234567",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {

            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_mail),
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "safegass@gmail.com",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {
                activity.sendMail()
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_facebook),
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "facebook/:safegass",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {

            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_instagram),
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "instagram/:safegass",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent)
            ),
            onClick = {

            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_twitter),
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "twitter/:safegass",
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
    }
}