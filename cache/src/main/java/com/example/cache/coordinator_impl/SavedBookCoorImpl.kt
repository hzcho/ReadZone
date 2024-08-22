package com.example.cache.coordinator_impl

import android.content.Context
import com.example.cache.coordinator.SavedBookCoordinator
import com.example.cache.dao.SavedBookDao
import com.example.cache.file_manager.SavedBookManager
import com.example.cache.utils.FileDefaults
import com.example.data.model.SavedBookEntity
import com.example.domain.model.Response
import com.example.domain.parameters.LocalPaginationParam
import com.example.domain.parameters.SaveBookParam
import java.io.File

class SavedBookCoorImpl(
    private val context: Context,
    private val savedBookManager: SavedBookManager,
    private val savedBookDao: SavedBookDao
):SavedBookCoordinator {
    override suspend fun saveBook(param: SaveBookParam): Response<SavedBookEntity> {
        return try {
            val defPath=getCurrentDir()+FileDefaults.BOOKS_FOLDER
            val newName=generateName(defPath, param.name)
            val path=defPath+newName

            savedBookManager.saveBook(path, param.bookRes)



            Response.Success()
        }catch (e:Exception){
            Response.Error(e)
        }
    }

    override suspend fun getSavedBooks(param: LocalPaginationParam): Response<List<SavedBookEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedBookRes(path: String): Response<ByteArray> {
        TODO("Not yet implemented")
    }

    private fun generateName(path: String, fileName: String): String {
        var newName = fileName
        var count = 0

        while (!checkFileExists(path + newName)) {
            count++
            newName = fileName + "(${count})"
        }

        return newName
    }

    private fun getCurrentDir()=context.filesDir.path

    private fun checkFileExists(path: String) = File(path).exists()

}