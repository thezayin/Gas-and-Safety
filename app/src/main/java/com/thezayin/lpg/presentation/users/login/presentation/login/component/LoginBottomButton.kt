package com.thezayin.lpg.presentation.users.login.presentation.login.component

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.lpg.common.dialogs.FieldsDialog
import com.thezayin.lpg.presentation.users.login.presentation.LoginViewModel

@Composable
fun LoginBottomButton(
    modifier: Modifier,
    navigator: DestinationsNavigator?,
    viewModel: LoginViewModel?,
    number: String
) {
    var checkField by remember { mutableStateOf(false) }
    val activity = LocalContext.current as Activity

    if (checkField) {
        FieldsDialog(showDialog = { checkField = it })
    }

    Button(
        onClick = {
            if (number.isEmpty()) {
                checkField = true
            } else {
                viewModel?.loginWithNumber(number, activity)
            }
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
            text = "Next",
            color = colorResource(id = com.thezayin.core.R.color.white),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
        )
    }
}

@Composable
@Preview
fun LoginBottomButtonPreview() {
    LoginBottomButton(
        modifier = Modifier, navigator = null, viewModel = null, number = ""
    )
}