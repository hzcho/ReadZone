package com.example.domain.usecase.book

import com.example.domain.model.BookModel
import com.example.domain.model.Response
import com.example.domain.parameters.SearchParam
import com.example.domain.repository.BookRepository
import com.example.domain.usecase.UseCase

class SearchBooks(
    private val bookRepository:BookRepository
): UseCase<SearchParam, Response<List<BookModel>>> {
    override suspend fun invoke(param: SearchParam): Response<List<BookModel>> {
        return bookRepository.searchBooks(param=param)
    }
}