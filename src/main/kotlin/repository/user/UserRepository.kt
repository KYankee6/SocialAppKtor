package com.uicheon.repository.user

import com.uicheon.model.AuthResponse
import com.uicheon.model.SignInParams
import com.uicheon.model.SignUpParams
import com.uicheon.repository.util.Response

interface UserRepository {
    suspend fun signUp(params: SignUpParams): Response<AuthResponse>
    suspend fun signIn(params: SignInParams): Response<AuthResponse>
}