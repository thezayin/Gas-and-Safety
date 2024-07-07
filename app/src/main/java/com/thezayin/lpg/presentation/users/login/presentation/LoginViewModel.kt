package com.thezayin.lpg.presentation.users.login.presentation

import android.app.Activity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.users.login.domain.usecase.CreateUser
import com.thezayin.lpg.presentation.users.login.domain.usecase.VerifyOTP
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val createUser: CreateUser,
    private val verifyPTP: VerifyOTP
) : ViewModel() {
    private val _isQuerySuccessful = MutableStateFlow(GetSuccessState())
    val isQuerySuccessful = _isQuerySuccessful.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _verificationCode = MutableStateFlow(GetVerificationCodeState())
    val verificationCode = _verificationCode.asStateFlow()

    fun updateErrorState(boolean: Boolean) {
        _isQueryError.update {
            it.copy(isError = mutableStateOf(boolean))
        }
    }

    fun loginWithNumber(number: String, activity: Activity) = viewModelScope.launch {
        createUser(number, activity).collect { response ->
            when (response) {
                is Response.Success -> {
                    delay(8000L)
                    _isLoading.update {
                        it.copy(isLoading = mutableStateOf(false))
                    }
                    _isQuerySuccessful.update {
                        it.copy(isSuccess = mutableStateOf(true))
                    }
                    _verificationCode.update {
                        it.copy(verificationCode = mutableStateOf(response.data))
                    }
                }

                is Response.Error -> {

                    _isLoading.update {
                        it.copy(isLoading = mutableStateOf(false))
                    }

                    _isQuerySuccessful.update {
                        it.copy(
                            isSuccess = mutableStateOf(false)
                        )
                    }

                    _isQueryError.update {
                        it.copy(
                            isError = mutableStateOf(true),
                            errorMessage = mutableStateOf(response.e)
                        )
                    }
                }

                is Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = mutableStateOf(true))
                    }
                }
            }
        }
    }

    fun verifyOTPEntered(otp: String, verificationCode: String) = viewModelScope.launch {
        verifyPTP(otp, verificationCode).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isQuerySuccessful.update {
                        it.copy(isSuccess = mutableStateOf(true))
                    }
                }

                is Response.Error -> {
                    _isQueryError.update {
                        it.copy(
                            isError = mutableStateOf(true),
                            errorMessage = mutableStateOf(response.e)
                        )
                    }
                }

                is Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = mutableStateOf(true))
                    }
                }
            }
        }
    }

    data class GetVerificationCodeState(
        val verificationCode: MutableState<String> = mutableStateOf("")
    )

    data class GetLoadingState(
        val isLoading: MutableState<Boolean> = mutableStateOf(false)
    )

    data class GetSuccessState(
        val isSuccess: MutableState<Boolean> = mutableStateOf(false)
    )

    data class GetErrorState(
        val isError: MutableState<Boolean> = mutableStateOf(false),
        val errorMessage: MutableState<String> = mutableStateOf("")
    )
}