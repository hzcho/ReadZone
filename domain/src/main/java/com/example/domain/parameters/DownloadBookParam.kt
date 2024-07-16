package com.example.domain.parameters

data class DownloadBookParam(
    val id: String,
    val name: String,
    val authorName: String,
    val imageUrl: String,
    val description: String,
    val resUrl: String,
    val format: String,
    val categoryIds:List<String>
)
