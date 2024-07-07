package com.thezayin.lpg.presentation.users.profile.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.lpg.R
import com.thezayin.entities.ProfileModel

@Composable
fun ProfileData(
    profileModel: com.thezayin.entities.ProfileModel,
    onDeleteClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 400.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.semi_transparent)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .heightIn(max = 400.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onDeleteClick(profileModel.id!!) }
                        .padding(top = 20.dp)
                        .size(20.dp)
                )
            }
            Text(
                text = "${profileModel.address}",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 12.sp,
                color = colorResource(id = com.thezayin.core.R.color.black),
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold))
            )

            Text(
                text = "${profileModel.area},${profileModel.city}",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 12.sp,
                color = colorResource(id = com.thezayin.core.R.color.black),
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold))
            )
            Text(
                text = "${profileModel.name}",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 12.sp,
                color = colorResource(id = com.thezayin.core.R.color.black),
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular))
            )
            Text(
                text = "${profileModel.area}",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 12.sp,
                color = colorResource(id = com.thezayin.core.R.color.black),
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular))
            )
            Text(
                text = "${profileModel.email}",
                modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
                fontSize = 12.sp,
                color = colorResource(id = com.thezayin.core.R.color.black),
                fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_regular))
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