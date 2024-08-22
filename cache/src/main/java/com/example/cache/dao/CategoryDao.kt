package com.example.cache.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.model.SavedBookEntity

interface CategoryDao {
    @Query("select * from categories")
    suspend fun getCategories(limit:Int, offset:Int):List<SavedBookEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(savedBook: SavedBookEntity)
}