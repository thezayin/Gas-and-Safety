package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.thezayin.assets.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * Composable for displaying location details with dropdowns for selecting city, area,
 * and a text field for entering the address.
 *
 * @param modifier The Modifier to be applied to this layout.
 * @param callBack Function to be triggered when a city is selected, used to fetch areas for that city.
 * @param cityList List of available cities for selection.
 * @param areaList List of areas based on the selected city.
 * @param address State that holds the current address input.
 * @param selectedCity State that holds the currently selected city.
 * @param selectedArea State that holds the currently selected area.
 * @param selectedCityIndex State that holds the index of the selected city.
 * @param selectedAreaIndex State that holds the index of the selected area.
 * @param expandedCity State controlling whether the city dropdown is expanded.
 * @param expandedArea State controlling whether the area dropdown is expanded.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetails(
    modifier: Modifier = Modifier,
    callBack: (String) -> Unit,
    cityList: List<String>,
    areaList: List<String>,
    address: MutableState<TextFieldValue>,
    selectedCity: MutableState<String>,
    selectedArea: MutableState<String>,
    selectedCityIndex: MutableState<Int>,
    selectedAreaIndex: MutableIntState,
    expandedCity: MutableState<Boolean>,
    expandedArea: MutableState<Boolean>
) {
    // Layout column containing all city, area, and address fields
    Column(
        modifier = modifier
            .padding(horizontal = 15.sdp).padding(top = 10.sdp) // Add padding to the column
            .fillMaxWidth(), // Occupy full width
        horizontalAlignment = Alignment.Start, // Align contents to the start of the column
        verticalArrangement = Arrangement.Center // Center contents vertically
    ) {
        // City Selection
        Text(
            text = "Select City",
            modifier = Modifier.padding(bottom = 10.sdp),
            fontSize = 12.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold))
        )

        // City Dropdown Menu
        ExposedDropdownMenuBox(
            expanded = expandedCity.value, // Track whether dropdown is expanded
            onExpandedChange = {
                expandedCity.value = !expandedCity.value
            }, // Toggle dropdown state
            modifier = Modifier.fillMaxWidth() // Ensure dropdown occupies full width
        ) {
            // City TextField displaying the selected city
            TextField(
                value = cityList[selectedCityIndex.value], // Display selected city
                onValueChange = {}, // No changes allowed, read-only field
                readOnly = true, // Mark TextField as read-only
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCity.value) }, // Dropdown icon
                modifier = Modifier.menuAnchor()
                    .fillMaxWidth(), // Fill width and act as dropdown anchor
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), // Set keyboard type
                shape = RoundedCornerShape(8.sdp), // Rounded corner shape for TextField
                colors = TextFieldDefaults.colors( // Customize TextField colors
                    focusedContainerColor = colorResource(id = R.color.semi_transparent),
                    unfocusedContainerColor = colorResource(id = R.color.semi_transparent),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = colorResource(id = R.color.black),
                    unfocusedTextColor = colorResource(id = R.color.black)
                )
            )

            // Dropdown menu showing city options
            ExposedDropdownMenu(
                expanded = expandedCity.value,
                onDismissRequest = { expandedCity.value = false }, // Close menu when dismissed
                modifier = Modifier.fillMaxWidth()
            ) {
                cityList.forEachIndexed { index, city ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = city,
                                color = colorResource(id = R.color.grey),
                                fontSize = 10.ssp,
                                fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                                fontWeight = if (index == selectedCityIndex.value) FontWeight.Bold else null // Bold for selected city
                            )
                        },
                        onClick = {
                            selectedCityIndex.value = index
                            expandedCity.value = false
                            selectedCity.value = city
                            callBack(city) // Trigger callback to fetch areas for selected city
                        }
                    )
                }
            }
        }

        // Area Selection (Only displayed if areas are available and a city is selected)
        if (areaList.isNotEmpty() && selectedCityIndex.value != 0) {
            Text(
                text = "Select Area",
                modifier = Modifier.padding(top = 10.sdp, bottom = 10.sdp),
                fontSize = 12.ssp,
                fontFamily = FontFamily(Font(R.font.noto_sans_bold))
            )

            // Area Dropdown Menu
            ExposedDropdownMenuBox(
                expanded = expandedArea.value, // Track whether dropdown is expanded
                onExpandedChange = {
                    expandedArea.value = !expandedArea.value
                }, // Toggle dropdown state
                modifier = Modifier.fillMaxWidth()
            ) {
                // Area TextField displaying the selected area
                TextField(
                    value = areaList[selectedAreaIndex.intValue], // Display selected area
                    onValueChange = {}, // No changes allowed, read-only field
                    readOnly = true, // Mark TextField as read-only
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedArea.value) }, // Dropdown icon
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), // Set keyboard type
                    shape = RoundedCornerShape(8.sdp),
                    colors = TextFieldDefaults.colors( // Customize TextField colors
                        focusedContainerColor = colorResource(id = R.color.semi_transparent),
                        unfocusedContainerColor = colorResource(id = R.color.semi_transparent),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = colorResource(id = R.color.black),
                        unfocusedTextColor = colorResource(id = R.color.black)
                    )
                )

                // Dropdown menu showing area options
                ExposedDropdownMenu(
                    expanded = expandedArea.value,
                    onDismissRequest = { expandedArea.value = false }, // Close menu when dismissed
                    modifier = Modifier.fillMaxWidth()
                ) {
                    areaList.forEachIndexed { index, area ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = area,
                                    color = colorResource(id = R.color.grey),
                                    fontSize = 10.ssp,
                                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                                    fontWeight = if (index == selectedAreaIndex.intValue) FontWeight.Bold else null // Bold for selected area
                                )
                            },
                            onClick = {
                                selectedAreaIndex.intValue = index
                                expandedArea.value = false
                                selectedArea.value = area
                            }
                        )
                    }
                }
            }
        }

        // Address Input Field (Only displayed if an area is selected)
        if (selectedAreaIndex.intValue != 0) {
            Text(
                text = "Address:",
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.black),
                fontSize = 12.ssp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                modifier = Modifier.padding(top = 10.sdp, bottom = 5.sdp)
            )

            // Address TextField
            TextField(
                value = address.value, // Display current address
                onValueChange = { address.value = it }, // Update address state on change
                placeholder = {
                    Text(
                        text = "Enter Address",
                        color = colorResource(id = R.color.grey),
                        fontSize = 10.ssp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold))
                    )
                },
                singleLine = true, // Restrict input to a single line
                modifier = Modifier
                    .fillMaxWidth() // Fill the entire width
                    .height(45.sdp), // Set fixed height for the address input
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(8.sdp),
                colors = TextFieldDefaults.colors( // Customize TextField colors
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
}