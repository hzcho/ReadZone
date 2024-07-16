package com.example.domain.usecase.auth

import com.example.domain.model.Response
import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.UseCase

class CheckUserReg(
    private val authRepository: AuthRepository
): UseCase<Unit, Response<Boolean>> {
    override suspend fun invoke(param: Unit): Response<Boolean> {
        return authRepository.checkUserReg()
    }
}