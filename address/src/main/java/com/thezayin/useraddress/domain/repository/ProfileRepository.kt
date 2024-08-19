package com.thezayin.useraddress.domain.repository

import com.thezayin.entities.ProfileModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun addProfile(
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String?
    ): Flow<Response<Boolean>>

    fun updateProfileById(
        id: Int,
        name: String,
        phoneNumber: String,
        address: String,
        email: String,
    ): Flow<Response<Boolean>>

    fun deleteProfileById(id: Int): Flow<Response<Boolean>>
    fun deleteAllProfiles(): Flow<Response<Boolean>>
    fun getAllProfiles(): Flow<Response<List<ProfileModel>>>
    fun getProfileById(id: Int): Flow<Response<ProfileModel>>
}