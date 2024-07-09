package com.thezayin.useraddress.domain.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface DeleteAllProfiles : suspend () -> Flow<Response<Boolean>>
class DeleteAllProfilesImpl(private val repository: com.thezayin.useraddress.domain.repository.ProfileRepository) :
    DeleteAllProfiles {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.deleteAllProfiles()
}