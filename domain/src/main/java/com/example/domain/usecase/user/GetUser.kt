package com.example.domain.usecase.user

import com.example.domain.model.Response
import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.UseCase

class GetUser(
    private val userRepository: UserRepository
): UseCase<Unit, Response<UserModel>> {
    override suspend fun invoke(param: Unit): Response<UserModel> {
        return userRepository.getUser()
    }
}