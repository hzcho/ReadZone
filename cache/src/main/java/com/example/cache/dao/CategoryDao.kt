package com.example.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.model.CategoryEntity
import com.example.cache.model.SavedBookEntity

@Dao
interface CategoryDao {
    @Query("select * from categories")
    suspend fun getCategories():List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(savedBook: SavedBookEntity)
}