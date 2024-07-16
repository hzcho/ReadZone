package com.example.remote.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class CategoryModel(
    @DocumentId
    val id:String="",
    val name:String="",
    val rank:Int=0
){
    companion object{
        const val NAME="name"
        const val BOOK_IDS="bookIds"
    }
}
