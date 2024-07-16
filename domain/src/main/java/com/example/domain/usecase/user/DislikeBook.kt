package com.example.domain.usecase.user

import com.example.domain.model.Response
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.UseCase

class DislikeBook(
    private val userRepository: UserRepository
): UseCase<String, Response<Boolean>> {
    override suspend fun invoke(param: String): Response<Boolean> {
        val isDisliked = userRepository.checkBookIsDisliked(bookId = param)
        if (isDisliked is Response.Error) return Response.Error(isDisliked.error)
        if (isDisliked is Response.Success && isDisliked.data) {
            return Response.Error(IllegalStateException("Book already disliked by the user"))
        }

        val isLiked = userRepository.checkBookIsLiked(bookId = param)
        if (isLiked is Response.Error) return Response.Error(isLiked.error)
        if (isLiked is Response.Success && isLiked.data) {
            val unlikeResponse = userRepository.unlikeBook(bookId = param)
            if (unlikeResponse is Response.Error) return Response.Error(unlikeResponse.error)
        }

        return userRepository.dislikeBook(bookId = param)
    }
}