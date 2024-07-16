package com.example.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_books")
data class SavedBookEntity(
    @PrimaryKey
    val savedBookId: String,
    val name: String,
    val authorName: String,
    val imagePath: String,
    val description: String,
    val format: String,
    val resPath: String,
    val currentPage: Int,
    val allPages: Int
)
