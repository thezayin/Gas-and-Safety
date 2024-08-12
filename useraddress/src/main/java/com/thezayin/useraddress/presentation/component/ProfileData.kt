package com.thezayin.useraddress.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.core.R
import com.thezayin.entities.ProfileModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ProfileData(
    profileModel: ProfileModel,
    onDeleteClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 10.sdp)
            .fillMaxWidth()
            .heightIn(max = 400.sdp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.semi_transparent)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.sdp)
                .fillMaxWidth()
                .heightIn(max = 400.sdp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onDeleteClick(profileModel.id!!) }
                        .padding(top = 10.sdp)
                        .size(18.sdp)
                )
            }
            Text(
                text = "${profileModel.address}",
                modifier = Modifier.padding(top = 5.sdp),
                fontSize = 10.ssp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_bold))
            )

            Text(
                text = "${profileModel.area},${profileModel.city}",
                modifier = Modifier.padding(top = 5.sdp),
                fontSize = 10.ssp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_bold))
            )
            Text(
                text = "${profileModel.name}",
                modifier = Modifier.padding(top = 5.sdp),
                fontSize = 10.ssp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )
            Text(
                text = "${profileModel.area}",
                modifier = Modifier.padding(top = 5.sdp),
                fontSize = 10.ssp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )
            Text(
                text = "${profileModel.email}",
                modifier = Modifier.padding(top = 5.sdp, bottom = 15.sdp),
                fontSize = 10.ssp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )
        }
    }
}

@Composable
@Preview
fun ProfileDataPreview() {
    ProfileData(
        profileModel = ProfileModel(
            name = "Name",
            phoneNumber = "1234567890",
            email = "email@email.com",
            address = "Address",
            area = "Area",
            city = "City"
        ),
        onDeleteClick = {}
    )
}