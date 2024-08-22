package com.example.cache.mapper

import com.example.data.model.SavedBookEntity

object SavedBookMapper {
    fun fromCache(type: com.example.cache.model.SavedBookEntity):SavedBookEntity=SavedBookEntity(
        id =type.savedBookId,
        name =type.name,
        authorName =type.authorName,
        imagePath =type.imagePath,
        description =type.description,
        format =type.format,
        resPath =type.resPath,
        currentPage =type.currentPage,
        allPages =type.allPages
    )
}