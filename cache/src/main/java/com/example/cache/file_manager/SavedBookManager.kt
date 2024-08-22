package com.example.cache.file_manager

import com.example.domain.model.Response
import java.io.InputStream

interface SavedBookManager {
    suspend fun saveBook(path:String, content:InputStream): Response<Boolean>
    suspend fun readBook(path:String):Response<InputStream>
}