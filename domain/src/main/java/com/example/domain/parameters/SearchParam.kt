package com.example.domain.parameters

data class SearchParam(
    val limit:Int,
    val lastIndex:String?,
    val query:String
)