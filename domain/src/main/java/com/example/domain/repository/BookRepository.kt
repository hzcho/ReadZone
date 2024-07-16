package com.example.domain.repository

import com.example.domain.model.BookModel
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.parameters.SearchParam

interface BookRepository {
    suspend fun getBooks(param: PaginationParam): Response<List<BookModel>>
    suspend fun getBooksByIds(ids:List<String>):Response<List<BookModel>>
    suspend fun searchBooks(param: SearchParam):Response<List<BookModel>>
    suspend fun downloadBook(url:String):Response<ByteArray>
    suspend fun downloadBookImage(url:String):Response<ByteArray>
}