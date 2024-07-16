package com.example.cache.model

import androidx.room.Entity

@Entity(primaryKeys = ["savedBookId", "categoryId"])
data class SavedBookCategoryRef(
    val savedBookId:String,
    val categoryId:String
)