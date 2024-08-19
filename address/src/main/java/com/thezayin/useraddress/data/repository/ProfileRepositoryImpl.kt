package com.thezayin.useraddress.data.repository

import com.thezayin.databases.databasae.CartDatabase
import com.thezayin.entities.ProfileModel
import com.thezayin.framework.utils.Response
import com.thezayin.useraddress.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepositoryImpl(private val database: CartDatabase) : ProfileRepository {
    override fun addProfile(
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String?
    ): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val profile = ProfileModel(
                name = name,
                phoneNumber = phoneNumber,
                address = address,
                area = area,
                city = city,
                email = email
            )
            database.profileDao().addProfile(profile)
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
            val profile = ProfileModel(
                name = name,
                phoneNumber = phoneNumber,
                address = address,
                email = email
            )
            database.profileDao().getAllProfiles().find { it.id == id }?.let {
                database.profileDao().updateProfileById(profile)
            }
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun deleteProfileById(id: Int): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            database.profileDao().deleteProfileById(id)
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun deleteAllProfiles(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            database.profileDao().deleteAllProfiles()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getAllProfiles(): Flow<Response<List<ProfileModel>>> = flow {
        try {
            emit(Response.Loading)
            val profileList = database.profileDao().getAllProfiles()
            emit(Response.Success(profileList))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getProfileById(id: Int): Flow<Response<ProfileModel>> =
        flow {
            try {
                emit(Response.Loading)
                val profile = database.profileDao().getProfile(id)
                emit(Response.Success(profile))
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "An error occurred"))
            }
        }
}