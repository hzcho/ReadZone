package com.example.data.repository

import com.example.data.model.BookEntity
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.parameters.SearchParam

interface BookRemote {
    suspend fun getBooks(param: PaginationParam): Response<List<BookEntity>>
    suspend fun getBooksByIds(ids:List<String>):Response<List<BookEntity>>
    suspend fun searchBooks(param: SearchParam):Response<List<BookEntity>>
    suspend fun downloadBook(url: String): Response<ByteArray>
    suspend fun downloadBookImage(url: String): Response<ByteArray>
}