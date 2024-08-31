package com.example.data.mapper

import com.example.data.model.Content

typealias domainContent=com.example.domain.model.Content

internal object ContentMapper {
    fun fromData(type: Content): domainContent{
        return domainContent(
            text = type.text
        )
    }
}