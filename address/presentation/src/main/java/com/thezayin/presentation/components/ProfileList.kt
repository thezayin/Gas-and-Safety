package com.thezayin.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.databases.model.ProfileModel

@Composable
fun ProfileList(
    profileList: List<ProfileModel>?,
    onDeleteClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        if (profileList != null) {
            items(profileList.size) { index ->
                ProfileData(
                    profileModel = profileList[index],
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}