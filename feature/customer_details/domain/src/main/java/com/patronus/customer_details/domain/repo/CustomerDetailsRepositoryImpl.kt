package com.patronus.customer_details.domain.repo

import com.patronus.api.CustomerDetails
import com.patronus.customer_details.api.repo.CustomerDetailsRepository
import com.patronus.customer_details.domain.extension.toCustomerDetails
import com.patronus.network.ApiService

class CustomerDetailsRepositoryImpl(private val apiService: ApiService): CustomerDetailsRepository {
    override suspend fun fetchCustomerDetails(customerId: Int): CustomerDetails {
        return apiService.fetchCustomerDetails(customerId).toCustomerDetails()
    }
}