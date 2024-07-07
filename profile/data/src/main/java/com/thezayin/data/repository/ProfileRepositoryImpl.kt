package com.thezayin.data.repository

import com.thezayin.data.databasae.ProfileDatabase
import com.thezayin.domain.ProfileRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepositoryImpl(
    private val profileDatabase: ProfileDatabase
) : ProfileRepository {
    override fun addProfile(
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String
    ): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val profile = com.thezayin.entities.ProfileModel(
                name = name,
                phoneNumber = phoneNumber,
                address = address,
                area = area,
                city = city,
                email = email
            )
            profileDatabase.profileDao().addProfile(profile)
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun updateProfileById(
        id: Int,
        name: String,
        phoneNumber: String,
        address: String,
        email: String,
    ): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val profile = com.thezayin.entities.ProfileModel(
                name = name,
                phoneNumber = phoneNumber,
                address = address,
                email = email
            )
            profileDatabase.profileDao().getAllProfiles().find { it.id == id }?.let {
                profileDatabase.profileDao().updateProfileById(profile)
            }
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun deleteProfileById(id: Int): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            profileDatabase.profileDao().deleteProfileById(id)
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun deleteAllProfiles(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            profileDatabase.profileDao().deleteAllProfiles()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getAllProfiles(): Flow<Response<List<com.thezayin.entities.ProfileModel>>> = flow {
        try {
            emit(Response.Loading)
            val profileList = profileDatabase.profileDao().getAllProfiles()
            emit(Response.Success(profileList))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getProfileById(id: Int): Flow<Response<com.thezayin.entities.ProfileModel>> =
        flow {
            try {
                emit(Response.Loading)
                val profile = profileDatabase.profileDao().getProfile(id)
                emit(Response.Success(profile))
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "An error occurred"))
            }
        }
}