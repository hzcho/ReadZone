package com.example.domain.usecase.saved_books

import com.example.domain.model.Response
import com.example.domain.repository.SavedBookRepository
import com.example.domain.usecase.UseCase
import java.io.InputStream

class GetSavedBookRes(
    private val savedBookRepository: SavedBookRepository
):UseCase<String, Response<InputStream>> {
    override suspend fun invoke(param: String): Response<InputStream> {
        return savedBookRepository.getSavedBookRes(param)
    }
}