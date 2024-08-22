package com.example.cache.file_manager_impl

import android.content.Context
import com.example.cache.file_manager.FileManager
import com.example.domain.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*

class FileManagerImpl(
    private val context: Context
) : FileManager {
    override suspend fun save(path: String, content: InputStream): Response<Boolean> {
        return try {
            val file = File(path)

            withContext(Dispatchers.IO) {
                FileOutputStream(file).use { outputStream ->
                    val buffer = ByteArray(8 * 1024)
                    var bytesRead: Int

                    while (content.read(buffer).also { bytesRead = it } != -1) {
                        outputStream.write(buffer, 0, bytesRead)
                    }
                }
            }

            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun readBook(path: String): Response<InputStream> {
        return try {
            val file = File(path)

            if (file.exists() && file.isFile) {
                val inputStream = withContext(Dispatchers.IO) {
                    FileInputStream(file)
                }
                Response.Success(inputStream)
            } else {
                Response.Error(FileNotFoundException("File not found or is not a file: $path"))
            }
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

}