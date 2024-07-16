package com.example.domain.usecase.user

import com.example.domain.model.Response
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.UseCase

class UnlikeBook(
    private val userRepository: UserRepository
): UseCase<String, Response<Boolean>> {
    override suspend fun invoke(param: String): Response<Boolean> {
        val isLiked = userRepository.checkBookIsLiked(bookId = param)
        if (isLiked is Response.Error) return Response.Error(isLiked.error)
        if (isLiked is Response.Success && !isLiked.data) {
            return Response.Error(IllegalStateException("the book was not liked by the user"))
        }

        return userRepository.unlikeBook(bookId = param)
    }
}