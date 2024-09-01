package com.example.readingzone.di

import com.example.data.repository.SavedBookReader
import com.example.readers.readers.FB2Reader
import com.example.readers.readers_impl.FB2ReaderImpl
import com.example.readers.repository_impl.SavedBookReaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReadersModule {
    @Provides
    @Singleton
    fun provideFB2Reader():FB2Reader{
        return FB2ReaderImpl()
    }

    @Provides
    @Singleton
    fun provideSavedBookReader(fB2Reader: FB2Reader):SavedBookReader{
        return SavedBookReaderImpl(fB2Reader)
    }
}