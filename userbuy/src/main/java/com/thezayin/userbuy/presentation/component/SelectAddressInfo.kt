package com.thezayin.userbuy.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R
import com.thezayin.entities.ProfileModel

@Composable
fun SelectAddressInfo(
    modifier: Modifier = Modifier,
    profileModel: ProfileModel,
    selectedIndex: Int,
    onSelected: (ProfileModel, Int) -> Unit,
) {
    Card(
        modifier = modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
            .heightIn(max = 400.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (profileModel.id == selectedIndex) colorResource(id = R.color.green_level_2) else colorResource(
                id = R.color.semi_transparent
            )
        ),
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    if (profileModel.id != selectedIndex) onSelected(
                        profileModel,
                        profileModel.id!!
                    )
                }
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .heightIn(max = 400.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "${profileModel.address}",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 12.sp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_bold))
            )

            Text(
                text = "${profileModel.area},${profileModel.city}",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 12.sp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_bold))
            )
            Text(
                text = "${profileModel.name}",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 12.sp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )
            Text(
                text = "${profileModel.area}",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 12.sp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )
            Text(
                text = "${profileModel.email}",
                modifier = Modifier.padding(top = 5.dp, bottom = 20.dp),
                fontSize = 12.sp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )
        }
    }
}

@Composable
@Preview
fun SelectAddressInfoPreview() {
    SelectAddressInfo(
        profileModel = ProfileModel(
            id = 1,
            name = "John Doe",
            email = "",
            address = "1234 Main St",
            area = "Area",
            city = "City",
            phoneNumber = "1234567890"
        ),
        selectedIndex = 1,
        onSelected = { _, _ -> }
    )
}