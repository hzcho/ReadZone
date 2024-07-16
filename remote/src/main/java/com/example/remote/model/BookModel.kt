package com.example.remote.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class BookModel(
    @DocumentId
    val id:String="",
    val name:String="",
    val authorName:String="",
    val imageUrl:String="",
    val description:String="",
    val resUrl:String="",
    val format:String="",
    val likeCount:Int=0,
    val dislikeCount:Int=0
){
    companion object{
        const val NAME="name"
        const val LIKE_COUNT="likeCount"
        const val DISLIKE_COUNT="dislikeCount"
        const val CATEGORY_IDS="categoryIds"
    }
}