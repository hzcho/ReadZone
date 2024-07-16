package com.example.cache.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.model.SavedBookEntity

interface SavedBookDao {
    @Query("select * from saved_books limit :limit offset :offset")
    suspend fun getSavedBooks(limit:Int, offset:Int):List<SavedBookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedBook(savedBook:SavedBookEntity)
}