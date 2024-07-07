package com.thezayin.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.domain.ProfileRepository
import kotlinx.coroutines.flow.Flow

interface GetAllProfiles : suspend () -> Flow<Response<List<com.thezayin.entities.ProfileModel>>>
class GetAllProfilesImpl(private val repository: ProfileRepository) :
    GetAllProfiles {
    override suspend fun invoke(): Flow<Response<List<com.thezayin.entities.ProfileModel>>> = repository.getAllProfiles()
}