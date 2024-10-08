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
    expandedArea: MutableState<Boolean>,
) {

    Column(
        modifier = modifier.padding(horizontal = 15.sdp).padding(top = 10.sdp).fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Select City",
            modifier = Modifier.padding(bottom = 10.sdp),
            fontSize = 12.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold))
        )
        ExposedDropdownMenuBox(
            expanded = expandedCity.value,
            onExpandedChange = { expandedCity.value = !expandedCity.value },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = cityList[selectedCityIndex.value],
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCity.value) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(8.sdp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.semi_transparent),
                    unfocusedContainerColor = colorResource(id = R.color.semi_transparent),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = colorResource(id = R.color.black),
                    unfocusedTextColor = colorResource(id = R.color.black)
                )
            )
            ExposedDropdownMenu(modifier = Modifier.fillMaxWidth(),
                expanded = expandedCity.value,
                onDismissRequest = { expandedCity.value = false }) {
                cityList.forEachIndexed { index, item ->
                    DropdownMenuItem(text = {
                        Text(
                            text = item,
                            color = colorResource(id = R.color.grey),
                            fontSize = 10.ssp,
                            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                            fontWeight = if (index == selectedCityIndex.value) FontWeight.Bold else null
                        )
                    }, onClick = {
                        selectedCityIndex.value = index
                        expandedCity.value = false
                        selectedCity.value = item
                        callBack(item)
                    })
                }
            }
        }
        if (areaList.isNotEmpty() && selectedCityIndex.value != 0) {
            Text(
                text = "Select Area",
                modifier = Modifier.padding(top = 10.sdp, bottom = 10.sdp),
                fontSize = 12.ssp,
                fontFamily = FontFamily(Font(R.font.noto_sans_bold))
            )
            ExposedDropdownMenuBox(
                expanded = expandedArea.value,
                onExpandedChange = { expandedArea.value = !expandedArea.value },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = areaList[selectedAreaIndex.intValue],
                    onValueChange = {

                    },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedArea.value) },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(8.sdp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorResource(id = R.color.semi_transparent),
                        unfocusedContainerColor = colorResource(id = R.color.semi_transparent),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = colorResource(id = R.color.black),
                        unfocusedTextColor = colorResource(id = R.color.black)
                    )
                )

                ExposedDropdownMenu(modifier = Modifier.fillMaxWidth(),
                    expanded = expandedArea.value,
                    onDismissRequest = { expandedArea.value = false }) {
                    areaList.forEachIndexed { index, item ->
                        DropdownMenuItem(text = {
                            Text(
                                text = item,
                                color = colorResource(id = R.color.grey),
                                fontSize = 10.ssp,
                                fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                                fontWeight = if (index == selectedAreaIndex.intValue) FontWeight.Bold else null
                            )
                        }, onClick = {
                            selectedAreaIndex.intValue = index
                            expandedArea.value = false
                            selectedArea.value = item
                        })
                    }
                }
            }
        }
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
            TextField(
                value = address.value,
                onValueChange = {
                    address.value = it
                },
                placeholder = {
                    Text(
                        text = "Enter Address",
                        color = colorResource(id = R.color.grey),
                        fontSize = 10.ssp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().height(45.sdp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(8.sdp),
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
}