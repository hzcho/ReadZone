package com.example.cache.repository_impl

import com.example.cache.coordinator.SavedBookCoordinator
import com.example.cache.dao.SavedBookDao
import com.example.data.model.SavedBookEntity
import com.example.data.repository.SavedBookCache
import com.example.domain.model.Response
import com.example.domain.parameters.LocalPaginationParam
import com.example.domain.parameters.SaveBookParam
import java.io.InputStream

class SavedBookCacheImpl(
    private val savedBookCoor:SavedBookCoordinator
): SavedBookCache {
    override suspend fun saveBook(param: SaveBookParam): Response<SavedBookEntity> {
        return savedBookCoor.saveBook(param)
    }

    override suspend fun getSavedBooks(param: LocalPaginationParam): Response<List<SavedBookEntity>> {
        return savedBookCoor.getSavedBooks(param)
    }

    override suspend fun getSavedBookRes(path: String): Response<InputStream> {
        return savedBookCoor.getSavedBookRes(path)
    }
}