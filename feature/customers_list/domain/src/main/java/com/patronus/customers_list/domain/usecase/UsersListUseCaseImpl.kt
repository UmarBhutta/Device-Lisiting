package com.patronus.customers_list.domain.usecase

import com.patronus.customers_list.api.repo.CustomersListRepository
import com.patronus.customers_list.api.usecase.CustomersListUseCase
import com.patronus.customers_list.api.usecase.CustomersListUseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import kotlin.coroutines.CoroutineContext

class CustomersListUseCaseImpl(
    private val coroutineContext: CoroutineContext = Dispatchers.IO,
    private val customersListRepository: CustomersListRepository
) : CustomersListUseCase {
    override suspend fun invoke(): CustomersListUseCaseResult {
        return withContext(coroutineContext) {
            try {
                val customers = customersListRepository.fetchCustomers()
                CustomersListUseCaseResult.Success(customers)
            } catch (e: Exception) {
                CustomersListUseCaseResult.Error(e.cause)
            }
        }
    }
}