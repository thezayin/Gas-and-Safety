package com.thezayin.useraddress.domain.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetAllProfiles : suspend () -> Flow<Response<List<com.thezayin.entities.ProfileModel>>>
class GetAllProfilesImpl(private val repository: com.thezayin.useraddress.domain.repository.ProfileRepository) :
    GetAllProfiles {
    override suspend fun invoke(): Flow<Response<List<com.thezayin.entities.ProfileModel>>> = repository.getAllProfiles()
}