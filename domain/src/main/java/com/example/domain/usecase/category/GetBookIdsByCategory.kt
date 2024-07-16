package com.example.domain.usecase.category

import com.example.domain.parameters.CategoryBookIdsParam
import com.example.domain.model.Response
import com.example.domain.repository.CategoryRepository
import com.example.domain.usecase.UseCase

class GetBookIdsByCategory(
    private val categoryRepository: CategoryRepository
): UseCase<CategoryBookIdsParam, Response<List<String>>> {
    override suspend fun invoke(param: CategoryBookIdsParam):  Response<List<String>> {
        return categoryRepository.getBookIds(param=param)
    }
}