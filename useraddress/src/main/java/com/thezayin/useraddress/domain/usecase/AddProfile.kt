package com.thezayin.useraddress.domain.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface AddProfile :
    suspend (String, String, String, String, String, String) -> Flow<Response<Boolean>>

class AddProfileImpl(
    private val profileRepository: com.thezayin.useraddress.domain.repository.ProfileRepository
) : AddProfile {
    override suspend fun invoke(
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String,
    ): Flow<Response<Boolean>> =
        profileRepository.addProfile(name, phoneNumber, address, area, city, email)
}