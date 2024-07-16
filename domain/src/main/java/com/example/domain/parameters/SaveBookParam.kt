package com.example.domain.parameters

import com.example.domain.model.CategoryModel
import java.io.InputStream

data class SaveBookParam(
    val id:String,
    val name:String,
    val authorName:String,
    val description:String,
    val format:String,
    val categories:List<CategoryModel>,
    val imageBytes:ByteArray,
    val bookBytes:ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SaveBookParam

        if (id != other.id) return false
        if (name != other.name) return false
        if (authorName != other.authorName) return false
        if (description != other.description) return false
        if (format != other.format) return false
        if (categories != other.categories) return false
        if (!imageBytes.contentEquals(other.imageBytes)) return false
        if (!bookBytes.contentEquals(other.bookBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + authorName.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + format.hashCode()
        result = 31 * result + categories.hashCode()
        result = 31 * result + imageBytes.contentHashCode()
        result = 31 * result + bookBytes.contentHashCode()
        return result
    }
}
