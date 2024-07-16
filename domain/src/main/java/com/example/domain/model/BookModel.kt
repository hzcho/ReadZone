package com.example.domain.model

data class BookModel(
    val id:String,
    val name:String,
    val authorName:String,
    val imageUrl:String,
    val description:String,
    val resUrl:String,
    val format:String,
    val likeCount:Int,
    val dislikeCount:Int,
    val categoryIds:List<String>
)
