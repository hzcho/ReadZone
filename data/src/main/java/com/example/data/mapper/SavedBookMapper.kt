package com.example.data.mapper

import com.example.data.model.SavedBookEntity
import com.example.domain.model.SavedBookModel

internal object SavedBookMapper {
    fun mapFromData(type: SavedBookEntity):SavedBookModel{
        return SavedBookModel(
            id = type.id,
            name = type.name,
            authorName = type.authorName,
            imagePath = type.imagePath,
            description = type.description,
            format = type.format,
            resPath = type.resPath,
            currentPage = type.currentPage,
            allPages = type.allPages
        )
    }
}