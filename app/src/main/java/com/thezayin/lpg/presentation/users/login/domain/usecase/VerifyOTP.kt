package com.thezayin.lpg.presentation.users.login.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.users.login.data.remote.VerifyNumberRepositoryImpl
import kotlinx.coroutines.flow.Flow

interface VerifyOTP : suspend (String, String) -> Flow<Response<Boolean>>

class VerifyOTPImpl(private val repositoryImpl: VerifyNumberRepositoryImpl) : VerifyOTP {
    override suspend fun invoke(otp: String, verificationCode: String) =
        repositoryImpl.verifyOtp(otp, verificationCode)
}