package com.example.data.repository_impl

import com.example.data.mapper.ContentMapper
import com.example.data.mapper.SavedBookMapper
import com.example.data.repository.SavedBookCache
import com.example.data.repository.SavedBookReader
import com.example.domain.model.*
import com.example.domain.parameters.LocalPaginationParam
import com.example.domain.parameters.ReadParam
import com.example.domain.parameters.SaveBookParam
import com.example.domain.repository.SavedBookRepository
import java.io.InputStream

class SavedBookRepositoryImpl(
    private val savedBookCache: SavedBookCache,
    private val savedBookReader:SavedBookReader
):SavedBookRepository {
    override suspend fun saveBook(param: SaveBookParam): Response<SavedBookModel> {
        return when(
            val dataSavedBook=savedBookCache.saveBook(param=param)
        ){
            is Response.Success -> {
                val domainSavedBook=SavedBookMapper.mapFromData(dataSavedBook.data)

                Response.Success(domainSavedBook)
            }
            is Response.Error -> dataSavedBook
        }
    }

    override suspend fun getSavedBooks(param: LocalPaginationParam): Response<List<SavedBookModel>> {
        return when(
            val dataSavedBooks=savedBookCache.getSavedBooks(param=param)
        ){
            is Response.Success -> {
                val domainSavedBooks=dataSavedBooks.data.map {
                    SavedBookMapper.mapFromData(it)
                }

                Response.Success(domainSavedBooks)
            }
            is Response.Error -> dataSavedBooks
        }
    }

    override suspend fun getSavedBookRes(path: String): Response<InputStream> {
        return savedBookCache.getSavedBookRes(path=path)
    }

    override suspend fun read(param: ReadParam): Response<Content> {
        return when(
            val dataContent=savedBookReader.read(param)
        ){
            is Response.Error -> dataContent
            is Response.Success -> {
                val domainContent=ContentMapper.fromData(dataContent.data)

                Response.Success(domainContent)
            }
        }
    }
}