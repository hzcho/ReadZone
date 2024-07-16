package com.example.remote.repository_impl

import com.example.data.model.CategoryEntity
import com.example.data.repository.CategoryRemote
import com.example.domain.parameters.CategoryBookIdsParam
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.remote.mapper.CategoryMapper
import com.example.remote.service.CategoryService

class CategoryRemoteImpl(
    private val categoryService: CategoryService
):CategoryRemote {
    override suspend fun getCategories(param: PaginationParam): Response<List<CategoryEntity>> {
        return when(val remoteCategories=categoryService.getCategories(param=param)){
            is Response.Success->{
                val dataCategories=remoteCategories.data.map {category->
                    CategoryMapper.mapFromRemote(category)
                }

                Response.Success(dataCategories)
            }
            is Response.Error->remoteCategories
        }
    }

    override suspend fun getBookIds(param: CategoryBookIdsParam): Response<List<String>> {
        return categoryService.getBookIds(param)
    }

    override suspend fun getCategoriesByIds(ids: List<String>): Response<List<CategoryEntity>> {
        return when(val remoteCategories=categoryService.getCategoriesByIds(ids=ids)){
            is Response.Success -> {
                val dataCategories=remoteCategories.data.map { categoryService->
                    CategoryMapper.mapFromRemote(categoryService)
                }

                Response.Success(dataCategories)
            }
            is Response.Error -> remoteCategories
        }
    }
}