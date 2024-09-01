package com.example.readingzone.di

import android.content.Context
import com.example.cache.coordinator.SavedBookCoordinator
import com.example.cache.coordinator_impl.SavedBookCoorImpl
import com.example.cache.dao.CategoryDao
import com.example.cache.dao.SavedBookDao
import com.example.cache.database.MainDB
import com.example.cache.file_manager.FileManager
import com.example.cache.file_manager_impl.FileManagerImpl
import com.example.cache.repository_impl.SavedBookCacheImpl
import com.example.data.repository.SavedBookCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Provides
    @Singleton
    fun provideMainDB(context: Context):MainDB{
        return MainDB.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideCategoryDao(db:MainDB):CategoryDao{
        return db.categoryDao
    }

    @Provides
    @Singleton
    fun provideSavedBookDao(db:MainDB):SavedBookDao{
        return db.savedBookDao
    }

    @Provides
    @Singleton
    fun provideFileManager(context:Context): FileManager {
        return FileManagerImpl(context)
    }

    @Provides
    @Singleton
    fun provideSavedBookCoordinator(context:Context, fileManager: FileManager, savedBookDao: SavedBookDao):SavedBookCoordinator{
        return SavedBookCoorImpl(context, fileManager, savedBookDao)
    }

    @Provides
    @Singleton
    fun provideSavedBookCache(savedBookCoordinator: SavedBookCoordinator):SavedBookCache{
        return SavedBookCacheImpl(savedBookCoordinator)
    }
}