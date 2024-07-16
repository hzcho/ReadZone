package com.example.data.model

data class SavedBookEntity(
    val id: String,
    val name: String,
    val authorName: String,
    val imagePath: String,
    val description: String,
    val format: String,
    val resPath: String,
    val currentPage: Int,
    val allPages: Int
)
