package com.example.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.dao.SavedBookDao
import com.example.cache.model.CategoryEntity
import com.example.cache.model.SavedBookEntity
import kotlin.concurrent.Volatile

@Database(
    entities = [SavedBookEntity::class, CategoryEntity::class],
    version = 1
)
abstract class MainDb:RoomDatabase() {
    abstract val savedBookDao:SavedBookDao

    companion object{
        @Volatile
        private var INSTANCE:MainDb?=null

        fun getInstance(context:Context)= synchronized(this){
            INSTANCE ?: createDatabase(context).apply {
                INSTANCE=this
            }
        }

        fun createDatabase(context:Context)=Room.databaseBuilder(
            context =context,
            klass =MainDb::class.java,
            name ="main_db"
        ).build()
    }
}