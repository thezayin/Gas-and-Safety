package com.thezayin.useraddress.domain.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface DeleteProfileById : suspend (Int) -> Flow<Response<Boolean>>

class DeleteProfileByIdImpl(
    private val profileRepository: com.thezayin.useraddress.domain.repository.ProfileRepository
) : DeleteProfileById {
    override suspend fun invoke(id: Int): Flow<Response<Boolean>> =
        profileRepository.deleteProfileById(id)
}