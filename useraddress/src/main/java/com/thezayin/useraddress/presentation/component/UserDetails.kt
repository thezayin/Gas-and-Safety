package com.thezayin.useraddress.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R

@Composable
fun UserDetails(
    name: MutableState<TextFieldValue>,
    phoneNumber: MutableState<TextFieldValue>,
    email: MutableState<TextFieldValue>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
    ) {
        Text(
            text = "Name:",
            textAlign = TextAlign.Start,
            color = colorResource(id = com.thezayin.core.R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
        )
        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            placeholder = {
                Text(
                    text = "Enter Name",
                    color = colorResource(id = com.thezayin.core.R.color.grey),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.semi_transparent),
                unfocusedContainerColor = colorResource(id = R.color.semi_transparent),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedTextColor = colorResource(id = R.color.black)
            )
        )
        Text(
            text = "Phone Number:",
            textAlign = TextAlign.Start,
            color = colorResource(id = com.thezayin.core.R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
        )
        TextField(
            value = phoneNumber.value,
            onValueChange = {
                if (it.text.length > 11) {
                    return@TextField
                }
                phoneNumber.value = it
            },
            placeholder = {
                Text(
                    text = "03121234567",
                    color = colorResource(id = com.thezayin.core.R.color.grey),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.semi_transparent),
                unfocusedContainerColor = colorResource(id = R.color.semi_transparent),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedTextColor = colorResource(id = R.color.black)
            )
        )
        Text(
            text = "Email:",
            textAlign = TextAlign.Start,
            color = colorResource(id = com.thezayin.core.R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
        )
        TextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            placeholder = {
                Text(
                    text = "Enter Email",
                    color = colorResource(id = com.thezayin.core.R.color.grey),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.semi_transparent),
                unfocusedContainerColor = colorResource(id = R.color.semi_transparent),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedTextColor = colorResource(id = R.color.black)
            )
        )
    }
}

@Composable
@Preview
fun UserDetailsPreview() {
    UserDetails(
        name = remember { mutableStateOf(TextFieldValue()) },
        phoneNumber = remember { mutableStateOf(TextFieldValue()) },
        email = remember { mutableStateOf(TextFieldValue()) },
    )
}