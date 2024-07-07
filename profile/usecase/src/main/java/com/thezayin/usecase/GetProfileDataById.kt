package com.thezayin.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.domain.ProfileRepository
import kotlinx.coroutines.flow.Flow

interface GetProfileDataById : suspend (Int) -> Flow<Response<com.thezayin.entities.ProfileModel>>
class GetProfileDataByIdImpl(private val profileRepository: ProfileRepository) :
    GetProfileDataById {
    override suspend fun invoke(id: Int): Flow<Response<com.thezayin.entities.ProfileModel>> =
        profileRepository.getProfileById(id)
}