package com.example.remote.repository_impl

import com.example.data.model.UserEntity
import com.example.data.repository.UserRemote
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.remote.mapper.UserMapper
import com.example.remote.service.UserService

class UserRemoteImpl(
    private val userService: UserService
):UserRemote {
    override suspend fun getUser(): Response<UserEntity> {
        return when (val remoteUser = userService.getUser()) {
            is Response.Error -> remoteUser
            is Response.Success -> {
                val dataUser = UserMapper.mapFromRemote(
                    type = remoteUser.data
                )
                Response.Success(dataUser)
            }
        }
    }

    override suspend fun getFavoriteBookIds(param: PaginationParam): Response<List<String>> {
        return userService.getFavoriteBookIds(param)
    }

    override suspend fun likeBook(bookId: String): Response<Boolean> {
        return userService.likeBook(bookId=bookId)
    }

    override suspend fun unlikeBook(bookId: String): Response<Boolean> {
        return userService.unlikeBook(bookId=bookId)
    }

    override suspend fun dislikeBook(bookId: String): Response<Boolean> {
        return userService.dislikeBook(bookId=bookId)
    }

    override suspend fun undislikeBook(bookId: String): Response<Boolean> {
        return userService.undislikeBook(bookId=bookId)
    }

    override suspend fun addToFavorites(bookId: String): Response<Boolean> {
        return userService.addToFavorites(bookId=bookId)
    }

    override suspend fun removeFromFavorites(bookId: String): Response<Boolean> {
        return userService.removeFromFavorites(bookId=bookId)
    }

    override suspend fun checkBookIsLiked(bookId: String):Response<Boolean>  {
        return userService.checkBookIsLiked(bookId=bookId)
    }

    override suspend fun checkBookIsDisliked(bookId: String):Response<Boolean>  {
        return userService.checkBookIsDisliked(bookId=bookId)
    }

    override suspend fun checkBookIsFavorite(bookId: String): Response<Boolean> {
        return userService.checkBookIsFavorite(bookId=bookId)
    }
}