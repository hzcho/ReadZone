package com.example.remote.service

import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.parameters.SearchParam
import com.example.remote.model.BookModel
import java.io.InputStream

interface BookService {
    suspend fun getBooks(param: PaginationParam): Response<List<BookModel>>
    suspend fun getBooksByIds(ids:List<String>): Response<List<BookModel>>
    suspend fun searchBooks(param: SearchParam):Response<List<BookModel>>
    suspend fun getCategoryIds(bookId:String):Response<List<String>>
    suspend fun downloadBook(url: String): Response<InputStream>
    suspend fun downloadBookImage(url: String): Response<InputStream>
}