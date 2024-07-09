package com.thezayin.useraddress.domain.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface UpdateProfileById :
    suspend (Int,String, String, String, String) -> Flow<Response<Boolean>>

class UpdateProfileByIdImpl(
    private val profileRepository: com.thezayin.useraddress.domain.repository.ProfileRepository
) : UpdateProfileById {
    override suspend fun invoke(
        id:Int,
        name: String,
        phoneNumber: String,
        address: String,
        email: String,
    ): Flow<Response<Boolean>> =
        profileRepository.updateProfileById(id,name, phoneNumber, address, email)
}