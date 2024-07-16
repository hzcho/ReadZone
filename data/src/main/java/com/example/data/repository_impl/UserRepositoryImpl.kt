package com.example.data.repository_impl

import com.example.data.mapper.UserMapper
import com.example.data.repository.UserRemote
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemote: UserRemote
):UserRepository {
    override suspend fun getUser(): Response<UserModel> {
        return when(val dataUser=userRemote.getUser()){
            is Response.Error->{
                Response.Error(error = dataUser.error)
            }
            is Response.Success->{
                val domainUser=UserMapper.mapFromData(dataUser.data)
                Response.Success(domainUser)
            }
        }
    }

    override suspend fun getFavoriteBookIds(param: PaginationParam): Response<List<String>> {
        return userRemote.getFavoriteBookIds(param=param)
    }

    override suspend fun likeBook(bookId: String): Response<Boolean> {
        return userRemote.likeBook(bookId=bookId)
    }

    override suspend fun unlikeBook(bookId: String): Response<Boolean> {
        return userRemote.unlikeBook(bookId=bookId)
    }

    override suspend fun dislikeBook(bookId: String): Response<Boolean> {
        return userRemote.dislikeBook(bookId=bookId)
    }

    override suspend fun undislikeBook(bookId: String): Response<Boolean> {
        return userRemote.undislikeBook(bookId=bookId)
    }

    override suspend fun addToFavorites(bookId: String): Response<Boolean> {
        return userRemote.addToFavorites(bookId=bookId)
    }

    override suspend fun removeFromFavorites(bookId: String): Response<Boolean> {
        return userRemote.removeFromFavorites(bookId=bookId)
    }

    override suspend fun checkBookIsLiked(bookId: String):Response<Boolean> {
        return userRemote.checkBookIsLiked(bookId=bookId)
    }

    override suspend fun checkBookIsDisliked(bookId: String):Response<Boolean> {
        return userRemote.checkBookIsDisliked(bookId=bookId)
    }

    override suspend fun checkBookIsFavorite(bookId: String): Response<Boolean> {
        return userRemote.checkBookIsFavorite(bookId=bookId)
    }
}