package com.example.remote.repository_impl

import com.example.data.repository.AuthRemote
import com.example.domain.model.Response
import com.example.domain.parameters.SignInParam
import com.example.domain.parameters.SignUpParam
import com.example.remote.service.AuthService

class AuthRemoteImpl(
    private val authService: AuthService
):AuthRemote {
    override suspend fun signUp(param: SignUpParam): Response<Boolean> {
        return authService.signUp(param=param)
    }

    override suspend fun signIn(param: SignInParam): Response<Boolean> {
        return authService.signIn(param=param)
    }

    override suspend fun signOut(): Response<Boolean> {
        return authService.signOut()
    }

    override suspend fun checkUserReg(): Response<Boolean> {
        return authService.checkUserReg()
    }

}