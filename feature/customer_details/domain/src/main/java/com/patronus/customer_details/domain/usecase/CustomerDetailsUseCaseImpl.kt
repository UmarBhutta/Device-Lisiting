package com.patronus.customer_details.domain.usecase

import com.patronus.customer_details.api.repo.CustomerDetailsRepository
import com.patronus.customer_details.api.usecase.CustomerDetailsUseCase
import com.patronus.customer_details.api.usecase.CustomerDetailsUseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CustomerDetailsUseCaseImpl(
    private val coroutineContext: CoroutineContext = Dispatchers.IO,
    private val customerDetailsRepository: CustomerDetailsRepository
): CustomerDetailsUseCase {
    override suspend fun invoke(customerId: Int): CustomerDetailsUseCaseResult {
        return withContext(coroutineContext) {
            try {
                val customer = customerDetailsRepository.fetchCustomerDetails(customerId)
                CustomerDetailsUseCaseResult.Success(customer)
            } catch (e: Exception) {
                CustomerDetailsUseCaseResult.Error(e.cause)
            }
        }
    }

}