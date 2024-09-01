package com.example.remote.service_impl

import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.parameters.SearchParam
import com.example.remote.model.BookModel
import com.example.remote.model.EmptyDocModel
import com.example.remote.service.BookService
import com.example.remote.utils.CollectionDefaults
import com.example.remote.utils.StorageDefaults
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.InputStream

class BookServiceImpl(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : BookService {
    override suspend fun getBooks(param: PaginationParam): Response<List<BookModel>> {
        return try {
            val collectionSnapshot = firestore.collection(CollectionDefaults.BOOKS)
                .orderBy(BookModel.NAME)
                .limit(param.limit.toLong())
                .startAt(param.page * param.limit)
                .get()
                .await()

            val remoteBooks = collectionSnapshot.mapNotNull { it.toObject(BookModel::class.java) }

            Response.Success(remoteBooks)

        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun getBooksByIds(ids: List<String>): Response<List<BookModel>> {
        return try {
            val books = mutableListOf<BookModel>()
            val chunkedIds = ids.chunked(10)

            chunkedIds.forEach { chunk ->
                val collectionSnapshot = firestore.collection(CollectionDefaults.BOOKS)
                    .whereIn(FieldPath.documentId(), chunk)
                    .get()
                    .await()

                collectionSnapshot.mapNotNull { snapshot ->
                    snapshot.toObject(BookModel::class.java).let { books.add(it) }
                }
            }

            Response.Success(books)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun searchBooks(param: SearchParam): Response<List<BookModel>> {
        return try {
            val lastVisible = if (param.lastIndex.isNullOrEmpty()) {
                null
            } else {
                firestore.collection(CollectionDefaults.BOOKS).document(param.lastIndex!!)
                    .get()
                    .await()
            }

            val query = firestore.collection(CollectionDefaults.BOOKS)
                .orderBy(BookModel.NAME)
                .limit(param.limit.toLong())
                .startAt(param.query)
                .endAt(param.query + "/uf8ff")

            val finalQuery = if (lastVisible == null) {
                query
            } else {
                query.startAt(lastVisible)
            }

            val collectionSnapshot = finalQuery
                .get()
                .await()

            val books = collectionSnapshot.map {
                it.toObject(BookModel::class.java)
            }
            Response.Success(books)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun getCategoryIds(bookId: String): Response<List<String>> {
        return try {
            val collectionSnapshot = firestore.collection(CollectionDefaults.BOOKS)
                .document(bookId).collection(BookModel.CATEGORY_IDS)
                .get()
                .await()

            val categoryIds = collectionSnapshot.map {
                it.toObject(EmptyDocModel::class.java).id
            }

            Response.Success(categoryIds)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun downloadBook(url: String): Response<InputStream> {
        return try {
            val bookRes = storage.reference.child(url)
                .stream
                .await()

            val inputStream=bookRes.stream

            Response.Success(inputStream)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun downloadBookImage(url: String): Response<InputStream> {
        return try {
            val bookRes = storage.reference.child(url)
                .stream
                .await()

            val inputStream=bookRes.stream

            Response.Success(inputStream)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}