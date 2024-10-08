package com.thezayin.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.databases.dao.ProfileDao
import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ProfileRepositoryImpl(
    private val profileDao: ProfileDao, private val firestore: FirebaseFirestore
) : ProfileRepository {
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
            profileDao.addProfile(profile)
            val userID = firestore.collection("user_info").document().id
            val order = ProfileModel(
                name = name,
                phoneNumber = phoneNumber,
                address = address,
                area = area,
                city = city,
                email = email
            )
            firestore.collection("user_info").document(userID).set(order).await()
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
                name = name, phoneNumber = phoneNumber, address = address, email = email
            )
            profileDao.getAllProfiles().find { it.id == id }?.let {
                profileDao.updateProfileById(profile)
            }
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun deleteProfileById(id: Int): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            profileDao.deleteProfileById(id)
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun deleteAllProfiles(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            profileDao.deleteAllProfiles()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getAllProfiles(): Flow<Response<List<ProfileModel>>> = flow {
        try {
            emit(Response.Loading)
            val profileList = profileDao.getAllProfiles()
            emit(Response.Success(profileList))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getProfileById(id: Int): Flow<Response<ProfileModel>> = flow {
        try {
            emit(Response.Loading)
            val profile = profileDao.getProfile(id)
            emit(Response.Success(profile))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}