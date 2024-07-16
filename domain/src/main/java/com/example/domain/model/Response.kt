package com.example.domain.model

sealed class Response<out T> {
    data class Success<out T>(
        val data: T
    ) : Response<T>()

    data class Error(
        val error: Throwable
    ) : Response<Nothing>()
}