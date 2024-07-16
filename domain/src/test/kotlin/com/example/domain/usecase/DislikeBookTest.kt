package com.example.domain.usecase

import com.example.domain.model.Response
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.user.DislikeBook
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DislikeBookTest {

    private val userRepository = mock<UserRepository>()
    private val dislikeBook = DislikeBook(userRepository)

    @Test
    fun `should return true when dislike is successful`() = runBlockingTest {
        val bookId = "1"
        whenever(userRepository.dislikeBook(bookId)).thenReturn(Response.Success(true))

        val result = dislikeBook(bookId)

        assertEquals(Response.Success(true), result)
    }
}
