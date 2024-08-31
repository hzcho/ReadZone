package com.example.readers.readers

import com.example.domain.model.Response
import com.example.domain.parameters.ReadParam
import com.example.readers.models.Content

interface FB2Reader {
    suspend fun read(param:ReadParam): Response<Content>
}