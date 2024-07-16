package com.example.domain.usecase

interface UseCase<in Parameter, out Result> {
    suspend operator fun invoke(param:Parameter):Result
}