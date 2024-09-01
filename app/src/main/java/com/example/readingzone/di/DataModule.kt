package com.example.readingzone.di

import com.example.data.repository.*
import com.example.data.repository_impl.*
import com.example.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideBookRepository(bookRemote: BookRemote):BookRepository{
        return BookRepositoryImpl(bookRemote=bookRemote)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRemote: UserRemote):UserRepository{
        return UserRepositoryImpl(userRemote=userRemote)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authRemote: AuthRemote):AuthRepository{
        return AuthRepositoryImpl(authRemote=authRemote)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryRemote: CategoryRemote):CategoryRepository{
        return CategoryRepositoryImpl(categoryRemote=categoryRemote)
    }

    @Provides
    @Singleton
    fun provideSavedBookRepository(savedBookCache:SavedBookCache, savedBookReader: SavedBookReader):SavedBookRepository{
        return SavedBookRepositoryImpl(savedBookCache, savedBookReader)
    }
}