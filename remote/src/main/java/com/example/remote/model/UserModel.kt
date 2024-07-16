package com.example.remote.model

data class UserModel(
    val name:String="",
    val email:String=""
){
    companion object{
        const val NAME="name"
        const val EMAIL="email"
        const val FAVORITE_BOOK_IDS="favoriteBookIds"
        const val LIKED_BOOK_IDS="likedBooksIds"
        const val DISLIKED_BOOK_IDS="dislikedBooksIds"
    }
}
