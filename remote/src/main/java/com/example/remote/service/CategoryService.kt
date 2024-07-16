package com.example.remote.service

import com.example.domain.parameters.CategoryBookIdsParam
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.remote.model.CategoryModel

interface CategoryService {
    suspend fun getCategories(param: PaginationParam):Response<List<CategoryModel>>
    suspend fun getBookIds(param: CategoryBookIdsParam): Response<List<String>>
    suspend fun getCategoriesByIds(ids:List<String>):Response<List<CategoryModel>>
}