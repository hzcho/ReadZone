package com.example.remote.service_impl

import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.remote.model.*
import com.example.remote.service.UserService
import com.example.remote.utils.CollectionDefaults
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserServiceImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : UserService {

    override suspend fun getUser(): Response<UserModel> {
        return try {
            val userId = getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val userDocRef = firestore.collection(CollectionDefaults.USERS).document(userId)

            val remoteUser =
                getUserModel(userDocRef) ?: return Response.Error(NullPointerException("User data is null"))

            Response.Success(remoteUser)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun likeBook(bookId: String): Response<Boolean> {
        return try {
            val userId = getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val userRef = firestore.collection(CollectionDefaults.USERS).document(userId)
            val bookRef = firestore.collection(CollectionDefaults.BOOKS).document(bookId)
            val likedBookRef = userRef.collection(UserModel.LIKED_BOOK_IDS).document(bookId)

            firestore.runTransaction { transaction ->
                transaction.set(
                    likedBookRef, EmptyDocModel()
                )
                transaction.update(bookRef, BookModel.LIKE_COUNT, FieldValue.increment(1))
                null
            }.await()

            val existsResponse=checkRefIsExists(likedBookRef)
            Response.Success(data = existsResponse)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun unlikeBook(bookId: String): Response<Boolean> {
        return try {
            val userId = getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val userRef = firestore.collection(CollectionDefaults.USERS).document(userId)
            val bookRef = firestore.collection(CollectionDefaults.BOOKS).document(bookId)
            val likedBookRef = userRef.collection(UserModel.LIKED_BOOK_IDS).document(bookId)

            firestore.runTransaction { transaction ->
                transaction.delete(likedBookRef)
                transaction.update(bookRef, BookModel.LIKE_COUNT, FieldValue.increment(-1))
                null
            }.await()

            val existsResponse=!checkRefIsExists(likedBookRef)
            Response.Success(data = existsResponse)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun dislikeBook(bookId: String): Response<Boolean> {
        return try {
            val userId = getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val userRef = firestore.collection(CollectionDefaults.USERS).document(userId)
            val bookRef = firestore.collection(CollectionDefaults.BOOKS).document(bookId)
            val dislikedBookRef = userRef.collection(UserModel.DISLIKED_BOOK_IDS).document(bookId)

            firestore.runTransaction { transaction ->
                transaction.set(
                    dislikedBookRef, EmptyDocModel()
                )
                transaction.update(bookRef, BookModel.DISLIKE_COUNT, FieldValue.increment(1))
                null
            }.await()

            val existsResponse=checkRefIsExists(dislikedBookRef)
            Response.Success(data = existsResponse)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun undislikeBook(bookId: String): Response<Boolean> {
        return try {
            val userId = getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val userRef = firestore.collection(CollectionDefaults.USERS).document(userId)
            val bookRef = firestore.collection(CollectionDefaults.BOOKS).document(bookId)
            val dislikedBookRef = userRef.collection(UserModel.DISLIKED_BOOK_IDS).document(bookId)

            firestore.runTransaction { transaction ->
                transaction.delete(dislikedBookRef)
                transaction.update(bookRef, BookModel.DISLIKE_COUNT, FieldValue.increment(-1))
                null
            }.await()

            val existsResponse=!checkRefIsExists(dislikedBookRef)
            Response.Success(data = existsResponse)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun addToFavorites(bookId: String): Response<Boolean> {
        return try {
            val userId = getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val userRef = firestore.collection(CollectionDefaults.USERS).document(userId)

            val favoriteBookRef = userRef.collection(UserModel.FAVORITE_BOOK_IDS).document(bookId)
            favoriteBookRef.set(
                EmptyDocModel()
            )

            val existsResponse = checkRefIsExists(favoriteBookRef)

            Response.Success(data = existsResponse)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun removeFromFavorites(bookId: String): Response<Boolean> {
        return try {
            val userId = getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val userRef = firestore.collection(CollectionDefaults.USERS).document(userId)

            val favoriteBookRef = userRef.collection(UserModel.FAVORITE_BOOK_IDS).document(bookId)
            favoriteBookRef.delete()
            val existsResponse = !checkRefIsExists(favoriteBookRef)

            Response.Success(data = existsResponse)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun checkBookIsLiked(bookId: String):Response<Boolean> {
        return try {
            val userId=getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val likedBookRef=firestore.collection(CollectionDefaults.USERS)
                .document(userId)
                .collection(UserModel.LIKED_BOOK_IDS)
                .document(bookId)

            val existResponse=checkRefIsExists(likedBookRef)

            Response.Success(existResponse)
        }catch (e:Exception){
            Response.Error(e)
        }
    }

    override suspend fun checkBookIsDisliked(bookId: String):Response<Boolean> {
        return try {
            val userId=getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val dislikedBookRef=firestore.collection(CollectionDefaults.USERS)
                .document(userId)
                .collection(UserModel.DISLIKED_BOOK_IDS)
                .document(bookId)

            val existResponse=checkRefIsExists(dislikedBookRef)

            Response.Success(existResponse)
        }catch (e:Exception){
            Response.Error(e)
        }
    }

    override suspend fun checkBookIsFavorite(bookId: String): Response<Boolean> {
        return try {
            val userId=getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val favoriteBookRef=firestore.collection(CollectionDefaults.USERS)
                .document(userId)
                .collection(UserModel.FAVORITE_BOOK_IDS)
                .document(bookId)

            val existResponse=checkRefIsExists(favoriteBookRef)

            Response.Success(existResponse)
        }catch (e:Exception){
            Response.Error(e)
        }
    }

    override suspend fun getFavoriteBookIds(param: PaginationParam): Response<List<String>> {
        return try {
            val userId=getUserId() ?: return Response.Error(NullPointerException("User ID is null"))
            val collectionSnapshot=firestore.collection(CollectionDefaults.USERS).document(userId).collection(UserModel.FAVORITE_BOOK_IDS)
                .orderBy(EmptyDocModel.CREATED_AT)
                .limit(param.limit.toLong())
                .startAt(param.limit*param.limit)
                .get()
                .await()

            val favoriteBookIds=collectionSnapshot.map {
                it.toObject(EmptyDocModel::class.java).id
            }

            Response.Success(favoriteBookIds)
        }catch (e:Exception){
            Response.Error(e)
        }
    }

    private suspend fun checkRefIsExists(documentRef:DocumentReference)=documentRef.get().await().exists()

    private suspend fun getUserModel(userDocRef: DocumentReference): UserModel? {
        val userSnapshot = userDocRef.get().await()
        return userSnapshot.toObject(UserModel::class.java)
    }

    private fun getUserId(): String? = auth.currentUser?.uid
}
