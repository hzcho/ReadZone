package com.example.data.mapper

import com.example.data.model.CategoryEntity
import com.example.domain.model.CategoryModel

internal object CategoryMapper {
    fun mapFromData(type:CategoryEntity): CategoryModel {
        return CategoryModel(
            id =type.id,
            name =type.name,
            rank = type.rank
        )
    }

    fun mapToData(type:CategoryModel):CategoryEntity{
        return CategoryEntity(
            id = type.id,
            name = type.name,
            rank = type.rank
        )
    }
}