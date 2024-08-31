package com.example.data.repository

import com.example.data.model.Content
import com.example.domain.model.Response
import com.example.domain.parameters.ReadParam

interface SavedBookReader {
    suspend fun read(param: ReadParam): Response<Content>
}