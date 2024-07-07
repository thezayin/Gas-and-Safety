package com.thezayin.domain

import com.thezayin.framework.utils.Response
import com.thezayin.entities.ProfileModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun addProfile(
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String
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
    fun getAllProfiles(): Flow<Response<List<com.thezayin.entities.ProfileModel>>>
    fun getProfileById(id: Int): Flow<Response<com.thezayin.entities.ProfileModel>>
}