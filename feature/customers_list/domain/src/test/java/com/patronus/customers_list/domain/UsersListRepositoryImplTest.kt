package com.patronus.customers_list.domain

import com.patronus.network.ApiService
import com.patronus.customers_list.api.repo.CustomersListRepository
import com.patronus.customers_list.domain.repo.CustomersListRepositoryImpl
import com.patronus.network.model.CustomersObject
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CustomersListRepositoryImplTest {
    @MockK
    private lateinit var apiService: ApiService
    private lateinit var repository: CustomersListRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = CustomersListRepositoryImpl(apiService)
    }

    @Test
    fun `verify customer list call`() = runBlocking {
        coEvery {
            apiService.fetchCustomersList()
        } returns mockk<CustomersObject>(relaxed = true).copy(customers = listOf(mockk(relaxed = true)))

        repository.fetchCustomers()

        coVerify { apiService.fetchCustomersList() }
    }
}