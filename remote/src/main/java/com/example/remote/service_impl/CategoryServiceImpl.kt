package com.example.remote.service_impl

import com.example.domain.parameters.CategoryBookIdsParam
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.remote.model.CategoryModel
import com.example.remote.model.EmptyDocModel
import com.example.remote.service.CategoryService
import com.example.remote.utils.CollectionDefaults
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CategoryServiceImpl(
    private val firestore: FirebaseFirestore
) : CategoryService {
    override suspend fun getCategories(param: PaginationParam): Response<List<CategoryModel>> {
        return try {
            val categoriesRef = firestore.collection(CollectionDefaults.CATEGORIES)

            val collectionSnapshot = categoriesRef
                .orderBy(CategoryModel.NAME)
                .limit(param.limit.toLong())
                .startAt(param.limit * param.page)
                .get()
                .await()
            val remoteCategory=collectionSnapshot.map { documentSnapshot ->
                getCategory(documentSnapshot.reference)
                    ?: return Response.Error(NullPointerException("Category data is null"))
            }

            Response.Success(remoteCategory)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun getCategoriesByIds(ids: List<String>): Response<List<CategoryModel>> {
        return try {
            val categories= mutableListOf<CategoryModel>()
            val chunkedIds=ids.chunked(10)

            chunkedIds.forEach { chunk->
                val collectionSnapshot=firestore.collection(CollectionDefaults.CATEGORIES)
                    .whereIn(FieldPath.documentId(), chunk)
                    .get()
                    .await()

                collectionSnapshot.mapNotNull { docSnapshot->
                    docSnapshot.toObject(CategoryModel::class.java).let { categories.add(it) }
                }
            }
            Response.Success(categories)
        }catch (e:Exception){
            Response.Error(e)
        }
    }

    override suspend fun getBookIds(param: CategoryBookIdsParam): Response<List<String>> {
        return try {
            val collectionSnapshot = firestore.collection(CollectionDefaults.CATEGORIES).document(param.categoryId)
                .collection(CategoryModel.BOOK_IDS)
                .orderBy(EmptyDocModel.CREATED_AT)
                .limit(param.limit.toLong())
                .startAt(param.limit * param.page)
                .get()
                .await()

            val bookIds = collectionSnapshot.mapNotNull { it.id }

            Response.Success(bookIds)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    private suspend fun getCategory(categoryRef: DocumentReference): CategoryModel? {
        val categorySnapshot = categoryRef.get().await()
        return categorySnapshot.toObject(CategoryModel::class.java)
    }
}