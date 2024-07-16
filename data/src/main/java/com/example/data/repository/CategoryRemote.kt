package com.example.data.repository

import com.example.data.model.CategoryEntity
import com.example.domain.model.CategoryModel
import com.example.domain.parameters.CategoryBookIdsParam
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response

interface CategoryRemote {
    suspend fun getCategories(param: PaginationParam): Response<List<CategoryEntity>>
    suspend fun getBookIds(param: CategoryBookIdsParam):Response<List<String>>
    suspend fun getCategoriesByIds(ids: List<String>): Response<List<CategoryEntity>>
}