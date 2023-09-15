package com.patronus.customers_list.api.usecase

import com.patronus.customers_list.api.Customer


interface CustomersListUseCase {
    suspend operator fun invoke(): CustomersListUseCaseResult
}

sealed class CustomersListUseCaseResult {
    data class Success(val users: List<Customer>): CustomersListUseCaseResult()
    data class Error(val throwable: Throwable?): CustomersListUseCaseResult()
}