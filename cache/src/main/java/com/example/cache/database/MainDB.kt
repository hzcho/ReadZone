package com.example.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.dao.CategoryDao
import com.example.cache.dao.SavedBookDao
import com.example.cache.model.CategoryEntity
import com.example.cache.model.SavedBookEntity
import kotlin.concurrent.Volatile

@Database(
    entities = [SavedBookEntity::class, CategoryEntity::class],
    version = 1
)
abstract class MainDB:RoomDatabase() {
    abstract val savedBookDao:SavedBookDao
    abstract val categoryDao:CategoryDao

    companion object{
        @Volatile
        private var INSTANCE:MainDB?=null

        fun getInstance(context:Context)= synchronized(this){
            INSTANCE ?: createDatabase(context).apply {
                INSTANCE=this
            }
        }

        private fun createDatabase(context:Context)=Room.databaseBuilder(
            context =context,
            klass =MainDB::class.java,
            name ="main_db"
        ).build()
    }
}