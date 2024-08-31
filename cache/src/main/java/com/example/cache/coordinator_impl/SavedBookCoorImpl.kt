package com.example.cache.coordinator_impl

import android.content.Context
import com.example.cache.coordinator.SavedBookCoordinator
import com.example.cache.dao.SavedBookDao
import com.example.cache.file_manager.FileManager
import com.example.cache.mapper.SavedBookMapper
import com.example.cache.utils.FileDefaults
import com.example.data.model.SavedBookEntity
import com.example.domain.model.Response
import com.example.domain.parameters.LocalPaginationParam
import com.example.domain.parameters.SaveBookParam
import java.io.File
import java.io.InputStream

typealias cacheSavedBook=com.example.cache.model.SavedBookEntity

class SavedBookCoorImpl(
    private val context: Context,
    private val fileManager: FileManager,
    private val savedBookDao: SavedBookDao
) : SavedBookCoordinator {
    override suspend fun saveBook(param: SaveBookParam): Response<SavedBookEntity> {
        return try {
            val defPath = getCurrentDir()
            val commonName = generateName(defPath+ FileDefaults.BOOKS_FOLDER, param.name)
            val bookPath = defPath + commonName
            val imagePath=defPath+FileDefaults.BOOK_IMAGES_FOLDER+commonName

            val saveBookResult = fileManager.save(bookPath, param.bookRes)
            if (saveBookResult is Response.Error) return saveBookResult

            val saveImageResult=fileManager.save(imagePath, param.imageRes)
            if (saveImageResult is Response.Error) return saveImageResult

            val cacheSavedBook=cacheSavedBook(
                savedBookId = param.id,
                name = param.name,
                authorName = param.authorName,
                imagePath = imagePath,
                description = param.description,
                format = param.format,
                resPath = bookPath,
                currentPage = 0,
                allPages = 0 //TODO change
            )
            savedBookDao.insertSavedBook(savedBook =cacheSavedBook)

            val dataSavedBook=SavedBookMapper.fromCache(cacheSavedBook)

            Response.Success(dataSavedBook)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun getSavedBooks(param: LocalPaginationParam): Response<List<SavedBookEntity>> {
        return try {
            val cacheSavedBooks=savedBookDao.getSavedBooks(param.limit, param.limit*param.page)
            val dataSavedBooks=cacheSavedBooks.map { SavedBookMapper.fromCache(it) }

            Response.Success(dataSavedBooks)
        }catch (e: Exception){
            Response.Error(e)
        }
    }

    override suspend fun getSavedBookRes(path: String): Response<InputStream> {
        return fileManager.readBook(path)
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

    private fun getCurrentDir() = context.filesDir.path

    private fun checkFileExists(path: String) = File(path).exists()

}