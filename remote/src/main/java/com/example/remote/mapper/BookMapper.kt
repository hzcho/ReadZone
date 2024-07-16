package com.example.remote.mapper

import com.example.data.model.BookEntity
import com.example.remote.model.BookModel

internal object BookMapper {
    fun mapFromRemote(type:BookModel, categoryIds:List<String>):BookEntity{
        return BookEntity(
            id = type.id,
            name = type.name,
            authorName = type.authorName,
            imageUrl = type.imageUrl,
            description = type.description,
            resUrl = type.resUrl,
            format = type.format,
            likeCount = type.likeCount,
            dislikeCount = type.dislikeCount,
            categoryIds=categoryIds
        )
    }
}