package com.example.readers.readers_impl

import com.example.domain.model.Response
import com.example.domain.parameters.ReadParam
import com.example.readers.models.Content
import com.example.readers.readers.FB2Reader
import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.math.min

class FB2ReaderImpl : FB2Reader {
    override suspend fun read(param: ReadParam): Response<Content> {
        return try {
            val inputStream = param.res
            val charactersCount = param.charCount
            val page = param.page

            val textBuilder = StringBuilder()

            val factory = DocumentBuilderFactory.newInstance()
            val builder = factory.newDocumentBuilder()
            val document: Document = builder.parse(inputStream)

            document.documentElement.normalize()

            val bodyElement = document.getElementsByTagName("body").item(0)
            val paragraphs = bodyElement?.childNodes ?: return Response.Error(Error("empty childNodes"))

            for (i in 0 until paragraphs.length) {
                val paragraph = paragraphs.item(i)
                if (paragraph.nodeType == org.w3c.dom.Node.ELEMENT_NODE) {
                    textBuilder.append(paragraph.textContent).append("\n")
                }
            }

            val fullText = textBuilder.toString()
            val startIndex = (page - 1) * charactersCount
            val endIndex = min(startIndex + charactersCount, fullText.length)

            val pageText = if (startIndex < fullText.length) {
                fullText.substring(startIndex, endIndex)
            } else {
                ""
            }

            Response.Success(Content(pageText))
        } catch (e: Exception) {
            return Response.Error(e)
        }
    }
}
