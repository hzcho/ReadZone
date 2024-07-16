package com.example.data.repository_impl

import com.example.data.repository.AuthRemote
import com.example.domain.model.Response
import com.example.domain.parameters.SignInParam
import com.example.domain.parameters.SignUpParam
import com.example.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemote: AuthRemote
):AuthRepository {
    override suspend fun signUp(param: SignUpParam): Response<Boolean> {
        return authRemote.signUp(param=param)
    }

    override suspend fun signIn(param: SignInParam): Response<Boolean> {
        return authRemote.signIn(param=param)
    }

    override suspend fun signOut(): Response<Boolean> {
        return authRemote.signOut()
    }

    override suspend fun checkUserReg():Response<Boolean> {
        return authRemote.checkUserReg()
    }
}