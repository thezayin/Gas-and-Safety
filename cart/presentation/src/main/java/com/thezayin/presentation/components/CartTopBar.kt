package com.thezayin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * CartTopBar displays the top bar for the cart screen.
 *
 * This component includes a back button, a title, and a contact button.
 *
 * @param modifier Optional [Modifier] to customize the layout and appearance.
 * @param onBackClick Callback function invoked when the back button is clicked.
 * @param onContactClick Callback function invoked when the contact button is clicked.
 */
@Composable
fun CartTopBar(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onContactClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth() // Fill the entire width of the parent
            .padding(top = 20.sdp) // Top padding for spacing
            .padding(horizontal = 10.sdp), // Horizontal padding for spacing
        horizontalArrangement = Arrangement.SpaceBetween, // Space items evenly
        verticalAlignment = Alignment.CenterVertically // Center items vertically
    ) {
        // Back button
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back",
            modifier = Modifier
                .clickable { onBackClick() } // Invoke back click callback
                .size(25.sdp) // Size of the icon
        )

        // Title
        Text(
            text = "Cart",
            color = colorResource(id = R.color.black), // Text color
            fontSize = 12.ssp, // Font size
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)), // Font style
        )

        // Contact button
        Image(
            painter = painterResource(id = R.drawable.ic_call),
            contentDescription = "Contact",
            modifier = Modifier
                .clickable { onContactClick() } // Invoke contact click callback
                .size(20.sdp) // Size of the icon
        )
    }
}