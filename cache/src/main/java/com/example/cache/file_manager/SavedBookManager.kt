package com.example.cache.file_manager

import com.example.domain.model.Response

interface SavedBookManager {
    suspend fun saveBook(fileName:String, content:ByteArray): Response<String>
    suspend fun readBook(fileName:String):Response<ByteArray>
}