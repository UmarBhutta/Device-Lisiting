package com.patronus.customers_list.domain.repo

import com.patronus.network.ApiService
import com.patronus.customers_list.api.Customer
import com.patronus.customers_list.api.repo.CustomersListRepository
import com.patronus.customers_list.domain.extension.toCustomers

class CustomersListRepositoryImpl(private val apiService: ApiService): CustomersListRepository {
    override suspend fun fetchCustomers(): List<Customer> {
        return apiService.fetchCustomersList().customers.toCustomers()
    }

}