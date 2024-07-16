package com.example.cache.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SavedBookWithCategories(
    @Embedded val savedBook:SavedBookEntity,
    @Relation(
        parentColumn = "savedBookId",
        entityColumn = "categoryId",
        associateBy = Junction(SavedBookCategoryRef::class)
    )
    val categories:List<CategoryEntity>
)
