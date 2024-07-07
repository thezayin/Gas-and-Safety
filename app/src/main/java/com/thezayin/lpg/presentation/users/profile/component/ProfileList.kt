package com.thezayin.lpg.presentation.users.profile.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.entities.ProfileModel

@Composable
fun ProfileList(
    profileList: List<com.thezayin.entities.ProfileModel>,
    onDeleteClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(profileList.size) { index ->
            ProfileData(
                profileModel = profileList[index],
                onDeleteClick = onDeleteClick
            )
        }
    }
}