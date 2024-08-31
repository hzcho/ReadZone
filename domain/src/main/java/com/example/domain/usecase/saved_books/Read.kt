package com.example.domain.usecase.saved_books

import com.example.domain.model.Content
import com.example.domain.model.Response
import com.example.domain.parameters.PaginationParam
import com.example.domain.parameters.ReadParam
import com.example.domain.repository.SavedBookRepository
import com.example.domain.usecase.UseCase
import java.io.InputStream


class Read(
    private val savedBookRepository: SavedBookRepository
) : UseCase<ReadParam, Response<Content>> {

    override suspend fun invoke(param: ReadParam): Response<Content> {
        return savedBookRepository.read(param)
    }
}

