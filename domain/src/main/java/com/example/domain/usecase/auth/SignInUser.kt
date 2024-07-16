package com.example.domain.usecase.auth

import com.example.domain.model.Response
import com.example.domain.parameters.SignInParam
import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.UseCase

class SignInUser(
    private val authRepository: AuthRepository
): UseCase<SignInParam, Response<Boolean>> {
    override suspend fun invoke(param: SignInParam): Response<Boolean> {
        return authRepository.signIn(param=param)
    }
}