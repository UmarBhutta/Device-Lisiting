package com.patronus.customer_details.api.usecase

import com.patronus.api.CustomerDetails

interface CustomerDetailsUseCase {
    suspend operator fun invoke(customerId: Int): CustomerDetailsUseCaseResult
}

sealed class CustomerDetailsUseCaseResult {
    data class Success(val customerDetails: CustomerDetails): CustomerDetailsUseCaseResult()
    data class Error(val throwable: Throwable?): CustomerDetailsUseCaseResult()
}