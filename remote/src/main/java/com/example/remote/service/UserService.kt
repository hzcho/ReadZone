package com.example.remote.service

import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.remote.model.UserModel

interface UserService {
    suspend fun getUser(): Response<UserModel>
    suspend fun getFavoriteBookIds(param: PaginationParam):Response<List<String>>
    suspend fun likeBook(bookId: String): Response<Boolean>
    suspend fun unlikeBook(bookId: String): Response<Boolean>
    suspend fun dislikeBook(bookId: String): Response<Boolean>
    suspend fun undislikeBook(bookId: String): Response<Boolean>
    suspend fun addToFavorites(bookId: String): Response<Boolean>
    suspend fun removeFromFavorites(bookId: String): Response<Boolean>
    suspend fun checkBookIsLiked(bookId: String):Response<Boolean>
    suspend fun checkBookIsDisliked(bookId: String):Response<Boolean>
    suspend fun checkBookIsFavorite(bookId: String):Response<Boolean>
}