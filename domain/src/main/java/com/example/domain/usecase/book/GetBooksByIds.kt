package com.example.domain.usecase.book

import com.example.domain.model.BookModel
import com.example.domain.model.Response
import com.example.domain.repository.BookRepository
import com.example.domain.usecase.UseCase

class GetBooksByIds(
    private val bookRepository: BookRepository
): UseCase<List<String>, Response<List<BookModel>>> {
    override suspend fun invoke(param: List<String>): Response<List<BookModel>> {
        return bookRepository.getBooksByIds(ids = param)
    }
}