package com.thezayin.lpg.presentation.users.login.domain.repository

import android.app.Activity
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface VerifyNumberRepository {
    suspend fun createUserWithNumber(number: String, activity: Activity): Flow<Response<String>>
    suspend fun verifyOtp(otp: String, verificationCode: String): Flow<Response<Boolean>>
}