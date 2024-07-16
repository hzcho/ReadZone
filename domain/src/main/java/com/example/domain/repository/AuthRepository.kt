package com.example.domain.repository

import com.example.domain.model.Response
import com.example.domain.parameters.SignInParam
import com.example.domain.parameters.SignUpParam

interface AuthRepository {
    suspend fun signUp(param: SignUpParam): Response<Boolean>
    suspend fun signIn(param: SignInParam):Response<Boolean>
    suspend fun signOut():Response<Boolean>
    suspend fun checkUserReg():Response<Boolean>
}