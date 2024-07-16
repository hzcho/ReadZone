package com.example.domain.usecase.category

import com.example.domain.model.CategoryModel
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.repository.CategoryRepository
import com.example.domain.usecase.UseCase

class GetCategories(
    private val categoryRepository: CategoryRepository
): UseCase<PaginationParam, Response<List<CategoryModel>>> {
    override suspend fun invoke(param: PaginationParam): Response<List<CategoryModel>> {
        return categoryRepository.getCategories(param=param)
    }
}