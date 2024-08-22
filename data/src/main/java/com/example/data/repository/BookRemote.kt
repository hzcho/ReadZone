package com.example.data.repository

import com.example.data.model.BookEntity
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.parameters.SearchParam
import java.io.InputStream

interface BookRemote {
    suspend fun getBooks(param: PaginationParam): Response<List<BookEntity>>
    suspend fun getBooksByIds(ids:List<String>):Response<List<BookEntity>>
    suspend fun searchBooks(param: SearchParam):Response<List<BookEntity>>
    suspend fun downloadBook(url: String): Response<InputStream>
    suspend fun downloadBookImage(url: String): Response<InputStream>
}