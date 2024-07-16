package com.example.remote.repository_impl

import com.example.data.model.BookEntity
import com.example.data.repository.BookRemote
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.parameters.SearchParam
import com.example.remote.mapper.BookMapper
import com.example.remote.service.BookService

class BookRemoteImpl(
    private val bookService: BookService
) : BookRemote {
    override suspend fun getBooks(param: PaginationParam): Response<List<BookEntity>> {
        return when (val remoteBooks = bookService.getBooks(param)) {
            is Response.Success -> {
                val dataBooks=remoteBooks.data.map { book ->
                    val categoryIds= when(
                        val categoryIdsResponse=bookService.getCategoryIds(book.id)
                    ){
                        is Response.Error -> return categoryIdsResponse
                        is Response.Success -> categoryIdsResponse.data
                    }
                    BookMapper.mapFromRemote(book, categoryIds)
                }

                Response.Success(dataBooks)
            }

            is Response.Error -> remoteBooks
        }
    }

    override suspend fun getBooksByIds(ids:List<String>): Response<List<BookEntity>> {
        return when (val remoteBooks = bookService.getBooksByIds(ids=ids)) {
            is Response.Success -> {
                val dataBooks=remoteBooks.data.map { book ->
                    val categoryIds= when(
                        val categoryIdsResponse=bookService.getCategoryIds(book.id)
                    ){
                        is Response.Error -> return categoryIdsResponse
                        is Response.Success -> categoryIdsResponse.data
                    }
                    BookMapper.mapFromRemote(book, categoryIds)
                }

                Response.Success(dataBooks)
            }

            is Response.Error -> remoteBooks
        }
    }

    override suspend fun searchBooks(param: SearchParam): Response<List<BookEntity>> {
        return when(val remoteBooks=bookService.searchBooks(param=param)){
            is Response.Success -> {
                val dataBooks=remoteBooks.data.map {book->
                    val categoryIds= when(
                        val categoryIdsResponse=bookService.getCategoryIds(book.id)
                    ){
                        is Response.Error -> return categoryIdsResponse
                        is Response.Success -> categoryIdsResponse.data
                    }
                    BookMapper.mapFromRemote(book, categoryIds)
                }
                Response.Success(dataBooks)
            }
            is Response.Error -> remoteBooks
        }
    }

    override suspend fun downloadBook(url: String): Response<ByteArray> {
        TODO("Not yet implemented")
    }

    override suspend fun downloadBookImage(url: String): Response<ByteArray> {
        TODO("Not yet implemented")
    }
}