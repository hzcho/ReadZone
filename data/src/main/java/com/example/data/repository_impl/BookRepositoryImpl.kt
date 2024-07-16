package com.example.data.repository_impl

import com.example.data.mapper.BookMapper
import com.example.data.repository.BookRemote
import com.example.domain.model.BookModel
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.parameters.SearchParam
import com.example.domain.repository.BookRepository

class BookRepositoryImpl(
    private val bookRemote: BookRemote
) : BookRepository {
    override suspend fun getBooks(param: PaginationParam): Response<List<BookModel>> {
        return when (val dataBooks = bookRemote.getBooks(param = param)) {
            is Response.Success -> {
                val domainBooks = dataBooks.data.map { BookMapper.mapFromData(it) }
                Response.Success(domainBooks)
            }

            is Response.Error -> dataBooks
        }
    }

    override suspend fun getBooksByIds(ids: List<String>): Response<List<BookModel>> {
        return when (val dataBooks = bookRemote.getBooksByIds(ids = ids)) {
            is Response.Success -> {
                val domainBooks = dataBooks.data.map { BookMapper.mapFromData(it) }
                Response.Success(domainBooks)
            }

            is Response.Error -> dataBooks
        }
    }

    override suspend fun searchBooks(param: SearchParam): Response<List<BookModel>> {
        return when (val dataBooks = bookRemote.searchBooks(param = param)) {
            is Response.Success -> {
                val domainBooks = dataBooks.data.map { BookMapper.mapFromData(it) }
                Response.Success(domainBooks)
            }

            is Response.Error -> dataBooks
        }
    }

    override suspend fun downloadBook(url: String): Response<ByteArray> {
        return bookRemote.downloadBookImage(url=url)
    }

    override suspend fun downloadBookImage(url: String): Response<ByteArray> {
        return bookRemote.downloadBookImage(url=url)
    }
}