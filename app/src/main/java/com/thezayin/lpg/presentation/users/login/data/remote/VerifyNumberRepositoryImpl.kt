package com.thezayin.lpg.presentation.users.login.data.remote

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.users.login.domain.repository.VerifyNumberRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class VerifyNumberRepositoryImpl(
    private val auth: FirebaseAuth,
) : VerifyNumberRepository {

    private var isVerifySuccess = false

    override suspend fun createUserWithNumber(
        number: String,
        activity: Activity
    ): Flow<Response<String>> =
        callbackFlow {
            trySend(Response.Loading)

            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {}
                override fun onVerificationFailed(p0: FirebaseException) {
                    trySend(Response.Error(p0.message ?: p0.toString()))
                }

                override fun onCodeSent(
                    verificationCode: String,
                    p1: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verificationCode, p1)
                    trySend(Response.Success(verificationCode))
                }
            }

            val option = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(number)
                .setTimeout(60L, java.util.concurrent.TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(option)
            awaitClose { close() }
        }

    override suspend fun verifyOtp(otp: String, verificationCode: String): Flow<Response<Boolean>> =
        callbackFlow {
            trySend(Response.Loading)
            val credential = PhoneAuthProvider.getCredential(verificationCode, otp)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        isVerifySuccess = true
                        trySend(Response.Success(isVerifySuccess))
                    } else {
                        trySend(Response.Error(task.exception?.message ?: "An error occurred"))
                    }
                }
            awaitClose { close() }
        }
}