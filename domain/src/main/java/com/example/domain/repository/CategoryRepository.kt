package com.example.domain.repository

import com.example.domain.parameters.CategoryBookIdsParam
import com.example.domain.model.CategoryModel
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response

interface CategoryRepository {
    suspend fun getCategories(param: PaginationParam):Response<List<CategoryModel>>
    suspend fun getBookIds(param: CategoryBookIdsParam):Response<List<String>>
    suspend fun getCategoriesByIds(ids:List<String>):Response<List<CategoryModel>>
}