package com.example.remote.service

import com.example.domain.model.Response
import com.example.domain.parameters.SignInParam
import com.example.domain.parameters.SignUpParam

interface AuthService {
    suspend fun signUp(param: SignUpParam): Response<Boolean>
    suspend fun signIn(param: SignInParam): Response<Boolean>
    suspend fun signOut(): Response<Boolean>
    fun checkUserReg():Response<Boolean>
}