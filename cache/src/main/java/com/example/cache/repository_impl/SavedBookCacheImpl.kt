package com.example.cache.repository_impl

import com.example.data.model.SavedBookEntity
import com.example.data.repository.SavedBookCache
import com.example.domain.parameters.LocalPaginationParam
import com.example.domain.model.Response
import com.example.domain.parameters.SaveBookParam

class SavedBookCacheImpl(

): SavedBookCache {
    override suspend fun saveBook(param: SaveBookParam): Response<SavedBookEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedBooks(param: LocalPaginationParam): Response<List<SavedBookEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedBookRes(path: String): Response<ByteArray> {
        TODO("Not yet implemented")
    }
}