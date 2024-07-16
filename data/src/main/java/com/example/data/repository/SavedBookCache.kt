package com.example.data.repository

import com.example.data.model.SavedBookEntity
import com.example.domain.parameters.LocalPaginationParam
import com.example.domain.model.Response
import com.example.domain.parameters.SaveBookParam

interface SavedBookCache {
    suspend fun saveBook(param: SaveBookParam): Response<SavedBookEntity>
    suspend fun getSavedBooks(param: LocalPaginationParam): Response<List<SavedBookEntity>>
    suspend fun getSavedBookRes(path: String): Response<ByteArray>
}