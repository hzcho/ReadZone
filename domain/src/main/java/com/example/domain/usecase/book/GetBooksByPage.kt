package com.example.domain.usecase.book

import com.example.domain.model.BookModel
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.repository.BookRepository
import com.example.domain.usecase.UseCase

class GetBooksByPage(
    private val bookRepository: BookRepository
): UseCase<PaginationParam, Response<List<BookModel>>> {
    override suspend fun invoke(param: PaginationParam): Response<List<BookModel>> {
        return bookRepository.getBooks(param=param)
    }
}