package com.example.domain.parameters

import com.example.domain.model.CategoryModel
import java.io.InputStream

data class SaveBookParam(
    val id:String,
    val name:String,
    val authorName:String,
    val description:String,
    val format:String,
    val categories:List<CategoryModel>,
    val imageRes:InputStream,
    val bookRes:InputStream
)