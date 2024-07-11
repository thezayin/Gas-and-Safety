package com.thezayin.useraddress.domain.usecase

import com.thezayin.entities.ProfileModel
import com.thezayin.framework.utils.Response
import com.thezayin.useraddress.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

interface GetAllProfiles : suspend () -> Flow<Response<List<ProfileModel>>>
class GetAllProfilesImpl(private val repository: ProfileRepository) :
    GetAllProfiles {
    override suspend fun invoke(): Flow<Response<List<ProfileModel>>> = repository.getAllProfiles()
}