package com.thezayin.lpg.presentation.users.login.presentation.login.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.lpg.R

@Composable
fun EnterNumber(number: MutableState<TextFieldValue>) {
    Text(
        text = "Enter Your Number",
        modifier = Modifier.padding(top = 60.dp, bottom = 30.dp),
        fontSize = 28.sp,
        fontWeight = FontWeight.ExtraBold,
        color = colorResource(id = R.color.black),
        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold))
    )
    TextField(
        value = number.value,
        onValueChange = {
            if (it.text.length > 11) {
                return@TextField
            }
            number.value = it
        },
        placeholder = {
            Text(
                text = "+923001234567",
                color = colorResource(id = com.thezayin.core.R.color.grey),
                fontSize = 14.sp,
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
}

@Composable
@Preview
fun EnterNumberPreview() {
    EnterNumber(number = remember { mutableStateOf(TextFieldValue()) })
}