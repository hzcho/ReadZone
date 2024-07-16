package com.example.domain.usecase.auth

import com.example.domain.model.Response
import com.example.domain.parameters.SignUpParam
import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.UseCase

class SignUpUser(
    private val authRepository: AuthRepository
): UseCase<SignUpParam, Response<Boolean>> {
    override suspend fun invoke(param: SignUpParam): Response<Boolean> {
        return authRepository.signUp(param=param)
    }
}