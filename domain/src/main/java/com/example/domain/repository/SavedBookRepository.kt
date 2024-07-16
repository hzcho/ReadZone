package com.example.domain.repository

import com.example.domain.model.*
import com.example.domain.parameters.LocalPaginationParam
import com.example.domain.parameters.SaveBookParam

interface SavedBookRepository {
    suspend fun saveBook(param: SaveBookParam):Response<SavedBookModel>
    suspend fun getSavedBooks(param: LocalPaginationParam):Response<List<SavedBookModel>>
    suspend fun getSavedBookRes(path:String):Response<ByteArray>
}