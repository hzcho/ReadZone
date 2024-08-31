package com.example.readers.mapper

import com.example.readers.models.Content

typealias dataContent=com.example.data.model.Content

internal object ContentMapper {
    fun fromReaders(type:Content):dataContent{
        return dataContent(
            text = type.text
        )
    }
}