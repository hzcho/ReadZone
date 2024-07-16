package com.example.domain.usecase.user

import com.example.domain.model.Response
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.UseCase

class LikeBook(
    private val userRepository: UserRepository
) : UseCase<String, Response<Boolean>> {

    override suspend fun invoke(param: String): Response<Boolean> {
        val isLiked = userRepository.checkBookIsLiked(bookId = param)
        if (isLiked is Response.Error) return Response.Error(isLiked.error)
        if (isLiked is Response.Success && isLiked.data) {
            return Response.Error(IllegalStateException("Book already liked by the user"))
        }

        val isDisliked = userRepository.checkBookIsDisliked(bookId = param)
        if (isDisliked is Response.Error) return Response.Error(isDisliked.error)
        if (isDisliked is Response.Success && isDisliked.data) {
            val undislikeResponse = userRepository.undislikeBook(bookId = param)
            if (undislikeResponse is Response.Error) return Response.Error(undislikeResponse.error)
        }

        return userRepository.likeBook(bookId = param)
    }
}
