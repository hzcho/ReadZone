package com.example.readingzone.di

import com.example.data.repository.AuthRemote
import com.example.data.repository.BookRemote
import com.example.data.repository.CategoryRemote
import com.example.data.repository.UserRemote
import com.example.data.repository_impl.AuthRepositoryImpl
import com.example.data.repository_impl.BookRepositoryImpl
import com.example.data.repository_impl.CategoryRepositoryImpl
import com.example.data.repository_impl.UserRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.BookRepository
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.UserRepository
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
}