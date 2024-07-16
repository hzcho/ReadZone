package com.example.cache.file_manager_impl

import android.content.Context
import com.example.cache.file_manager.SavedBookManager
import com.example.cache.utils.FileDefaults
import com.example.domain.model.Response
import java.io.File

class SavedBookManagerImpl(
    private val context: Context
) : SavedBookManager {
    override suspend fun saveBook(fileName: String, content: ByteArray): Response<String> {
        return try {
            val pathDef = getCurrentDir() + FileDefaults.BOOKS_FOLDER + fileName
            val path = pathDef+generateName(pathDef, fileName)

            context.openFileOutput(path, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write(content)
            }

            return Response.Success(path)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun readBook(fileName: String): Response<ByteArray> {
        return try {
            val path=getCurrentDir()+fileName

            context.openFileInput(path).bufferedReader().useLines { lines->
                lines.
            }

            Response.Success()
        } catch (e: Exception) {
            Response.Error(e)
        }
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