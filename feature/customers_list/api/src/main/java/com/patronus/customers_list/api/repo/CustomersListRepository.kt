package com.patronus.customers_list.api.repo

import com.patronus.customers_list.api.Customer

interface CustomersListRepository {
    suspend fun fetchCustomers(): List<Customer>
}