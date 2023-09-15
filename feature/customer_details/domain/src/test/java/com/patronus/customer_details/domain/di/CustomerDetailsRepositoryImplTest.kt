package com.patronus.customer_details.domain.di

import com.patronus.api.CustomerDetails
import com.patronus.customer_details.api.repo.CustomerDetailsRepository
import com.patronus.customer_details.domain.repo.CustomerDetailsRepositoryImpl
import com.patronus.network.ApiService
import com.patronus.network.model.CustomerDetailsDto
import com.patronus.network.model.CustomersObject
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CustomerDetailsRepositoryImplTest {
    @MockK
    private lateinit var apiService: ApiService
    private lateinit var repository: CustomerDetailsRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = CustomerDetailsRepositoryImpl(apiService)
    }

    @Test
    fun `verify customer details api call`() = runBlocking {
        val id = 1
        coEvery {
            apiService.fetchCustomerDetails(id)
        } returns mockk<CustomerDetailsDto>(relaxed = true)

        repository.fetchCustomerDetails(id)

        coVerify { apiService.fetchCustomerDetails(id) }
    }
}