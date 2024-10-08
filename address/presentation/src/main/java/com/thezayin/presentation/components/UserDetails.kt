package com.thezayin.presentation.components

import androidx.compose.foundation.layout.*
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
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * A composable for input fields to enter user's name and phone number.
 *
 * This component consists of two text fields:
 * - One for the name of the user
 * - Another for the phone number
 *
 * @param name A mutable state holding the value of the name input.
 * @param phoneNumber A mutable state holding the value of the phone number input.
 */
@Composable
fun UserDetails(
    name: MutableState<TextFieldValue>, // State to store the user's name
    phoneNumber: MutableState<TextFieldValue>, // State to store the user's phone number
) {
    // Column to organize the input fields vertically
    Column(
        modifier = Modifier
            .fillMaxWidth() // Take up the full width of the parent
            .padding(horizontal = 15.sdp) // Add horizontal padding for spacing
    ) {
        // Name Input Field Label
        Text(
            text = "Name:", // Label for the name input
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black), // Label text color
            fontSize = 12.ssp, // Font size
            fontWeight = FontWeight.Bold, // Bold font weight
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)), // Custom bold font
            modifier = Modifier.padding(top = 10.sdp, bottom = 5.sdp) // Add top and bottom padding for the label
        )
        // Name Input Field
        TextField(
            value = name.value, // Current name input value
            onValueChange = {
                name.value = it // Update the state when the text changes
            },
            placeholder = {
                Text(
                    text = "Enter Name", // Placeholder text inside the input field
                    color = colorResource(id = R.color.grey), // Placeholder text color
                    fontSize = 10.ssp, // Placeholder font size
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)) // Custom font for placeholder
                )
            },
            singleLine = true, // Restrict input to a single line
            modifier = Modifier
                .fillMaxWidth() // Make the input field take up the full width
                .height(45.sdp), // Set a fixed height for the input field
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), // Set keyboard type for text input
            shape = RoundedCornerShape(8.sdp), // Rounded corners for the input field
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.semi_transparent), // Background color when focused
                unfocusedContainerColor = colorResource(id = R.color.semi_transparent), // Background color when unfocused
                focusedIndicatorColor = Color.Transparent, // No underline color when focused
                unfocusedIndicatorColor = Color.Transparent, // No underline color when unfocused
                focusedTextColor = colorResource(id = R.color.black), // Text color when focused
                unfocusedTextColor = colorResource(id = R.color.black) // Text color when unfocused
            )
        )

        // Phone Number Input Field Label
        Text(
            text = "Phone Number:", // Label for the phone number input
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black), // Label text color
            fontSize = 12.ssp, // Font size
            fontWeight = FontWeight.Bold, // Bold font weight
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)), // Custom bold font
            modifier = Modifier.padding(top = 10.sdp, bottom = 5.sdp) // Add top and bottom padding for the label
        )

        // Phone Number Input Field
        TextField(
            value = phoneNumber.value, // Current phone number input value
            onValueChange = {
                // Restrict the phone number length to 11 characters
                if (it.text.length > 11) {
                    return@TextField
                }
                phoneNumber.value = it // Update the state when the text changes
            },
            placeholder = {
                Text(
                    text = "03121234567", // Placeholder for phone number input
                    color = colorResource(id = R.color.grey), // Placeholder text color
                    fontSize = 10.ssp, // Placeholder font size
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)) // Custom font for placeholder
                )
            },
            singleLine = true, // Restrict input to a single line
            modifier = Modifier
                .fillMaxWidth() // Make the input field take up the full width
                .height(45.sdp), // Set a fixed height for the input field
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Set keyboard type for number input
            shape = RoundedCornerShape(8.sdp), // Rounded corners for the input field
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.semi_transparent), // Background color when focused
                unfocusedContainerColor = colorResource(id = R.color.semi_transparent), // Background color when unfocused
                focusedIndicatorColor = Color.Transparent, // No underline color when focused
                unfocusedIndicatorColor = Color.Transparent, // No underline color when unfocused
                focusedTextColor = colorResource(id = R.color.black), // Text color when focused
                unfocusedTextColor = colorResource(id = R.color.black) // Text color when unfocused
            )
        )
    }
}

/**
 * A preview function to display how the UserDetails composable looks with empty fields.
 * This is used only for development preview in Android Studio.
 */
@Composable
@Preview
fun UserDetailsPreview() {
    UserDetails(
        name = remember { mutableStateOf(TextFieldValue()) }, // Empty state for name preview
        phoneNumber = remember { mutableStateOf(TextFieldValue()) }, // Empty state for phone number preview
    )
}