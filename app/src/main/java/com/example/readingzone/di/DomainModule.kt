package com.example.readingzone.di

import com.example.domain.repository.*
import com.example.domain.usecase.auth.CheckUserReg
import com.example.domain.usecase.auth.SignInUser
import com.example.domain.usecase.auth.SignOutUser
import com.example.domain.usecase.auth.SignUpUser
import com.example.domain.usecase.book.GetBooksByIds
import com.example.domain.usecase.book.GetBooksByPage
import com.example.domain.usecase.book.SearchBooks
import com.example.domain.usecase.category.GetBookIdsByCategory
import com.example.domain.usecase.category.GetCategories
import com.example.domain.usecase.saved_books.GetSavedBookRes
import com.example.domain.usecase.saved_books.Read
import com.example.domain.usecase.user.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideGetBooksUseCase(bookRepository: BookRepository): GetBooksByPage {
        return GetBooksByPage(bookRepository = bookRepository)
    }

    @Provides
    @Singleton
    fun provideGetUser(userRepository: UserRepository): GetUser {
        return GetUser(userRepository=userRepository)
    }

    @Provides
    @Singleton
    fun provideSignUpUser(authRepository: AuthRepository): SignUpUser {
        return SignUpUser(authRepository=authRepository)
    }

    @Provides
    @Singleton
    fun provideSignInUser(authRepository: AuthRepository): SignInUser {
        return SignInUser(authRepository=authRepository)
    }

    @Provides
    @Singleton
    fun provideSignOutUser(authRepository: AuthRepository): SignOutUser {
        return SignOutUser(authRepository=authRepository)
    }

    @Provides
    @Singleton
    fun provideCheckUserReg(authRepository: AuthRepository): CheckUserReg {
        return CheckUserReg(authRepository=authRepository)
    }

    @Provides
    @Singleton
    fun provideGetBooksByIds(bookRepository: BookRepository): GetBooksByIds {
        return GetBooksByIds(bookRepository=bookRepository)
    }

    @Provides
    @Singleton
    fun provideGetCategories(categoryRepository: CategoryRepository): GetCategories {
        return GetCategories(categoryRepository=categoryRepository)
    }

    @Provides
    @Singleton
    fun provideLikeBook(userRepository: UserRepository): LikeBook {
        return LikeBook(userRepository=userRepository)
    }

    @Provides
    @Singleton
    fun provideUnlikeBook(userRepository: UserRepository): UnlikeBook {
        return UnlikeBook(userRepository=userRepository)
    }

    @Provides
    @Singleton
    fun provideDislikeBook(userRepository: UserRepository): DislikeBook {
        return DislikeBook(userRepository=userRepository)
    }

    @Provides
    @Singleton
    fun provideUndislikeBook(userRepository: UserRepository): UndislikeBook {
        return UndislikeBook(userRepository=userRepository)
    }

    @Provides
    @Singleton
    fun provideAddToFavorite(userRepository: UserRepository): AddToFavorite {
        return AddToFavorite(userRepository=userRepository)
    }

    @Provides
    @Singleton
    fun provideRemoveFromFavorite(userRepository: UserRepository): RemoveFromFavorites {
        return RemoveFromFavorites(userRepository=userRepository)
    }

    @Provides
    @Singleton
    fun provideGetBookIdsByCategory(categoryRepository: CategoryRepository): GetBookIdsByCategory {
        return GetBookIdsByCategory(categoryRepository=categoryRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteBooks(userRepository: UserRepository): GetFavoriteBookIds {
        return GetFavoriteBookIds(userRepository =userRepository)
    }

    @Provides
    @Singleton
    fun provideSearchBooks(bookRepository: BookRepository): SearchBooks {
        return SearchBooks(bookRepository =bookRepository)
    }

    @Provides
    @Singleton
    fun provideRead(savedBookRepository: SavedBookRepository):Read{
        return Read(savedBookRepository)
    }

    @Provides
    @Singleton
    fun provideGetSavedBookRes(savedBookRepository: SavedBookRepository):GetSavedBookRes{
        return GetSavedBookRes(savedBookRepository)
    }
}