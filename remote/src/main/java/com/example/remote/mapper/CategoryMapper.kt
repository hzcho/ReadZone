package com.example.remote.mapper

import com.example.data.model.CategoryEntity
import com.example.remote.model.CategoryModel

internal object CategoryMapper {
    fun mapFromRemote(type: CategoryModel):CategoryEntity{
        return CategoryEntity(
            id = type.id,
            name = type.name,
            rank = type.rank
        )
    }
}