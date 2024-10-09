package com.thezayin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * DeleteAllCart is a component that displays a button to empty the cart.
 *
 * @param emptyClick Callback function invoked when the "Empty" button is clicked.
 */
@Composable
fun DeleteAllCart(emptyClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 10.sdp) // Top padding for spacing
            .padding(horizontal = 10.sdp) // Horizontal padding for spacing
            .fillMaxWidth(), // Fill the entire width of the parent
        horizontalArrangement = Arrangement.End // Align items to the end
    ) {
        Card(
            modifier = Modifier
                .height(30.sdp) // Height of the card
                .width(60.sdp), // Width of the card
            shape = RoundedCornerShape(10.sdp), // Rounded corners
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.semi_transparent), // Background color of the card
            )
        ) {
            Row(
                modifier = Modifier
                    .clickable { emptyClick() } // Trigger the empty click callback
                    .padding(horizontal = 5.sdp) // Padding for the content inside the card
                    .fillMaxSize(), // Fill the size of the card
                horizontalArrangement = Arrangement.Center, // Center the content horizontally
                verticalAlignment = Alignment.CenterVertically // Center the content vertically
            ) {
                Text(
                    text = "Empty", // Text to display
                    color = colorResource(id = R.color.black), // Text color
                    fontSize = 8.ssp, // Font size
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)), // Font style
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_delete), // Delete icon
                    contentDescription = null, // No content description for decorative icons
                    modifier = Modifier
                        .padding(start = 2.sdp) // Padding to the start of the image
                        .size(10.sdp) // Size of the image
                )
            }
        }
    }
}

@Composable
@Preview
fun DeleteAllCartPreview() {
    DeleteAllCart {}
}