package com.example.data.mapper

import com.example.data.model.BookEntity
import com.example.domain.model.BookModel

internal object BookMapper {
    fun mapFromData(type: BookEntity): BookModel {
        return BookModel(
            id = type.id,
            name = type.name,
            authorName = type.authorName,
            imageUrl = type.imageUrl,
            description = type.description,
            resUrl = type.resUrl,
            format = type.format,
            likeCount = type.likeCount,
            dislikeCount = type.dislikeCount,
            categoryIds = type.categoryIds
        )
    }

    fun mapToData(type: BookModel): BookEntity {
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
            categoryIds = type.categoryIds
        )
    }
}