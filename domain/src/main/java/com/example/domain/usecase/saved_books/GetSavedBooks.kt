package com.example.domain.usecase.saved_books

import com.example.domain.parameters.LocalPaginationParam
import com.example.domain.model.Response
import com.example.domain.model.SavedBookModel
import com.example.domain.repository.SavedBookRepository
import com.example.domain.usecase.UseCase

class GetSavedBooks(
    private val savedBookRepository: SavedBookRepository
):UseCase<LocalPaginationParam, Response<List<SavedBookModel>>> {
    override suspend fun invoke(param: LocalPaginationParam): Response<List<SavedBookModel>> {
        return savedBookRepository.getSavedBooks(param=param)
    }
}