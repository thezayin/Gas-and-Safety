package com.thezayin.lpg.presentation.users.login.presentation.verify.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.lpg.presentation.users.login.presentation.LoginViewModel

@Composable
@Destination
fun VerifyBottomButton(
    modifier: Modifier,
    navigator: DestinationsNavigator?,
    viewModel: LoginViewModel?,
    otp: String,
    verificationCode: String
) {

    Button(
        onClick = {
            viewModel?.verifyOTPEntered(otp, verificationCode)
        },

        modifier = modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 25.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = com.thezayin.core.R.color.black),
        )
    ) {
        Text(
            text = "Submit",
            color = colorResource(id = com.thezayin.core.R.color.white),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
        )
    }
}

@Composable
@Preview
fun VerifyBottomButtonPreview() {
    VerifyBottomButton(
        modifier = Modifier,
        navigator = null,
        viewModel = null,
        otp = "123456",
        verificationCode = "+923001234567"
    )
}