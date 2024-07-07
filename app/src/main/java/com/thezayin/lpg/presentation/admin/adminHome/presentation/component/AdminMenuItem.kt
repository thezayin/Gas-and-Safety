package com.thezayin.lpg.presentation.admin.adminHome.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.lpg.R
import com.thezayin.lpg.destinations.AddProductScreenDestination
import com.thezayin.lpg.destinations.AdminProductScreenDestination
import com.thezayin.lpg.destinations.FetchOrdersScreenDestination
import com.thezayin.lpg.presentation.admin.adminHome.domain.model.AdminOptionMenuModel

@Composable
fun AdminMenuItem(
    option: AdminOptionMenuModel,
    modifier: Modifier,
    navigator: DestinationsNavigator,
) {
    Box(
        modifier = modifier
            .padding(vertical = 5.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = R.color.semi_transparent))
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                when (option.id) {
                    1 -> {
                        navigator.navigate(AdminProductScreenDestination)
                    }

                    2 -> {
                        navigator.navigate(AddProductScreenDestination)
                    }

                    3 -> {
                        navigator.navigate(FetchOrdersScreenDestination)
                    }
                }

            }
    ) {
        Text(
            text = option.title,
            color = colorResource(id = com.thezayin.core.R.color.black),
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}