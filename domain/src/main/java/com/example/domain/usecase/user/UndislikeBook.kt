package com.example.domain.usecase.user

import com.example.domain.model.Response
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.UseCase

class UndislikeBook(
    private val userRepository: UserRepository
): UseCase<String, Response<Boolean>> {
    override suspend fun invoke(param: String): Response<Boolean> {
        val isDisliked = userRepository.checkBookIsDisliked(bookId = param)
        if (isDisliked is Response.Error) return Response.Error(isDisliked.error)
        if (isDisliked is Response.Success && !isDisliked.data) {
            return Response.Error(IllegalStateException("the book was not disliked by the user"))
        }

        return userRepository.undislikeBook(bookId = param)
    }
}