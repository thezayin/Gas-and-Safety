package com.thezayin.useraddress.domain.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetProfileDataById : suspend (Int) -> Flow<Response<com.thezayin.entities.ProfileModel>>
class GetProfileDataByIdImpl(private val profileRepository: com.thezayin.useraddress.domain.repository.ProfileRepository) :
    GetProfileDataById {
    override suspend fun invoke(id: Int): Flow<Response<com.thezayin.entities.ProfileModel>> =
        profileRepository.getProfileById(id)
}