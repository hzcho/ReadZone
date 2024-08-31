package com.example.readers.repository_impl

import com.example.data.model.Content
import com.example.data.repository.SavedBookReader
import com.example.domain.model.Response
import com.example.domain.parameters.ReadParam
import com.example.readers.mapper.ContentMapper
import com.example.readers.readers.FB2Reader

class SavedBookReaderImpl(
    private val fB2Reader: FB2Reader
): SavedBookReader {
    override suspend fun read(param: ReadParam): Response<Content> {
        return when(
            val readersContent=fB2Reader.read(param)
        ){
            is Response.Error -> readersContent
            is Response.Success -> {
                val dataContent=ContentMapper.fromReaders(readersContent.data)

                Response.Success(dataContent)
            }
        }
    }
}