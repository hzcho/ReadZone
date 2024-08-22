package com.example.domain.usecase.book

import com.example.domain.model.*
import com.example.domain.parameters.DownloadBookParam
import com.example.domain.parameters.SaveBookParam
import com.example.domain.repository.BookRepository
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.SavedBookRepository
import com.example.domain.usecase.UseCase

class DownloadAndSaveBook(
    private val bookRepository: BookRepository,
    private val savedBookRepository: SavedBookRepository,
    private val categoryRepository:CategoryRepository
) : UseCase<DownloadBookParam, Response<SavedBookModel>> {
    override suspend fun invoke(param: DownloadBookParam): Response<SavedBookModel> {
        val bookRes = when (
            val bookResResponse = bookRepository.downloadBook(param.resUrl)
        ) {
            is Response.Error -> return bookResResponse
            is Response.Success -> bookResResponse.data
        }
        val imageRes = when (
            val imageResResponse = bookRepository.downloadBookImage(param.imageUrl)
        ) {
            is Response.Error -> return imageResResponse
            is Response.Success -> imageResResponse.data
        }
        val categoryIds=when(
            val categoryIdsResponse=categoryRepository.getCategoriesByIds(param.categoryIds)
        ){
            is Response.Error->return categoryIdsResponse
            is Response.Success->{
                categoryIdsResponse.data
            }
        }

        return savedBookRepository.saveBook(
            param = SaveBookParam(
                id = param.id,
                name = param.name,
                authorName = param.authorName,
                description = param.description,
                format = param.format,
                imageRes = imageRes,
                bookRes = bookRes,
                categories = categoryIds
            )
        )
    }
}