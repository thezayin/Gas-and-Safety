package com.thezayin.userbuy.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.entities.ProfileModel

@Composable
fun SelectAddressList(
    profileList: List<ProfileModel>,
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onSelected: (ProfileModel, Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(profileList.size) { index ->
            SelectAddressInfo(
                modifier = Modifier,
                profileModel = profileList[index],
                selectedIndex = selectedIndex,
                onSelected = onSelected,
            )
        }
    }
}