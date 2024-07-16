package com.example.domain.usecase.user

import com.example.domain.model.Response
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.UseCase

class AddToFavorite(
    private val userRepository: UserRepository
): UseCase<String, Response<Boolean>> {
    override suspend fun invoke(param: String): Response<Boolean> {
        val isFavorite=userRepository.checkBookIsFavorite(bookId = param)

        if (isFavorite is Response.Error) return Response.Error(isFavorite.error)
        if (isFavorite is Response.Success && isFavorite.data){
            return Response.Error(IllegalStateException("the book is already in favorites"))
        }

        return userRepository.addToFavorites(bookId = param)
    }
}