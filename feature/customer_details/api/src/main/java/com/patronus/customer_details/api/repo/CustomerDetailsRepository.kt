package com.patronus.customer_details.api.repo

import com.patronus.api.CustomerDetails

interface CustomerDetailsRepository {
    suspend fun fetchCustomerDetails(customerId: Int): CustomerDetails
}