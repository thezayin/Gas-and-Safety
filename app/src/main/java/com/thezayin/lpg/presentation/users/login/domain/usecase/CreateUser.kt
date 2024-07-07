package com.thezayin.lpg.presentation.users.login.domain.usecase

import android.app.Activity
import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.users.login.domain.repository.VerifyNumberRepository
import kotlinx.coroutines.flow.Flow

interface CreateUser : suspend (String, Activity) -> Flow<Response<String>>

class CreateUserImpl(private val repository: VerifyNumberRepository) : CreateUser {
    override suspend fun invoke(number: String, activity: Activity): Flow<Response<String>> =
        repository.createUserWithNumber(number, activity)
}