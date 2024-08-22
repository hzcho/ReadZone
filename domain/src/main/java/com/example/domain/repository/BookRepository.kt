package com.example.domain.repository

import com.example.domain.model.BookModel
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.parameters.SearchParam
import java.io.InputStream

interface BookRepository {
    suspend fun getBooks(param: PaginationParam): Response<List<BookModel>>
    suspend fun getBooksByIds(ids:List<String>):Response<List<BookModel>>
    suspend fun searchBooks(param: SearchParam):Response<List<BookModel>>
    suspend fun downloadBook(url:String):Response<InputStream>
    suspend fun downloadBookImage(url:String):Response<InputStream>
}