package com.example.domain.usecase.user

import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.UseCase

class GetFavoriteBookIds(
    private val userRepository: UserRepository
): UseCase<PaginationParam, Response<List<String>>> {
    override suspend fun invoke(param: PaginationParam): Response<List<String>> {
        return userRepository.getFavoriteBookIds(param=param)
    }
}