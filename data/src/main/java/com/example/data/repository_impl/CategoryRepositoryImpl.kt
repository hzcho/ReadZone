package com.example.data.repository_impl

import com.example.data.mapper.CategoryMapper
import com.example.data.repository.CategoryRemote
import com.example.domain.parameters.CategoryBookIdsParam
import com.example.domain.model.CategoryModel
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val categoryRemote: CategoryRemote
):CategoryRepository {
    override suspend fun getCategories(param: PaginationParam): Response<List<CategoryModel>> {
        return when(val dataCategories=categoryRemote.getCategories(param=param)){
            is Response.Success->{
                val domainCategories=dataCategories.data.map { CategoryMapper.mapFromData(it)}
                Response.Success(domainCategories)
            }
            is Response.Error-> dataCategories
        }
    }

    override suspend fun getBookIds(param: CategoryBookIdsParam): Response<List<String>> {
        return categoryRemote.getBookIds(param=param)
    }

    override suspend fun getCategoriesByIds(ids: List<String>): Response<List<CategoryModel>> {
        return when(val dataCategories=categoryRemote.getCategoriesByIds(ids=ids)){
            is Response.Error -> dataCategories
            is Response.Success -> {
                val domainCategories=dataCategories.data.map { CategoryMapper.mapFromData(it) }
                Response.Success(domainCategories)
            }
        }
    }
}