package com.thezayin.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable that displays a toggle button group where each button can be selected independently.
 * The group contains multiple states (e.g., "All", "In Progress", "Delivered", "Cancelled").
 *
 * @param onClick A lambda function that is invoked when a button is clicked, returning the selected index.
 */
@Composable
fun ToggleButton(
    onClick: (index: Int) -> Unit = {}
) {
    val cornerRadius = 8.sdp

    // Remember the selected index (null means none is selected initially)
    val (selectedIndex, onIndexSelected) = remember { mutableStateOf<Int?>(null) }

    // The items for the toggle button
    val items = listOf(
        "All",
        "In Progress",
        "Delivered",
        "Cancelled"
    )

    // Row container to display the toggle buttons horizontally
    Row(
        modifier = Modifier
            .padding(top = 20.sdp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Iterate over the items and create a button for each
        items.forEachIndexed { index, item ->
            OutlinedButton(
                // Modifier to adjust button position and z-index for the selected button
                modifier = Modifier
                    .offset(x = (-index).sdp) // Offset for proper button stacking
                    .zIndex(if (selectedIndex == index) 1f else 0f), // Elevate selected button

                // Handle button click
                onClick = {
                    onIndexSelected(index) // Update the selected index
                    onClick(index)         // Trigger the onClick lambda
                },

                // Define button shape based on position (left, right, or middle)
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStart = cornerRadius,
                        bottomStart = cornerRadius
                    ) // Left outer button
                    items.size - 1 -> RoundedCornerShape(
                        topEnd = cornerRadius,
                        bottomEnd = cornerRadius
                    ) // Right outer button
                    else -> RoundedCornerShape(0.dp) // Middle buttons have no rounded corners
                },

                // Define border stroke, making it slightly thicker for selected items
                border = BorderStroke(
                    width = 1.dp,
                    color = colorResource(id = R.color.semi_transparent)
                ),

                // Define the colors for the selected and unselected buttons
                colors = if (selectedIndex == index) {
                    ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                } else {
                    ButtonDefaults.outlinedButtonColors(
                        containerColor = colorResource(id = R.color.semi_transparent),
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                // Button text with color and font size adjustments based on selection state
                Text(
                    text = item,
                    color = if (selectedIndex == index) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.DarkGray.copy(alpha = 0.9f)
                    },
                    fontSize = 7.ssp
                )
            }
        }
    }
}

/**
 * A preview function to display the ToggleButton composable in the Android Studio preview.
 */
@Composable
@Preview
fun PreviewToggleButton() {
    ToggleButton()
}