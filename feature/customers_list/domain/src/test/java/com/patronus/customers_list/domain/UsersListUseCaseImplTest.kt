package com.patronus.customers_list.domain

import com.patronus.customers_list.api.Customer
import com.patronus.customers_list.api.usecase.CustomersListUseCase
import com.patronus.customers_list.api.usecase.CustomersListUseCaseResult
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CustomersListUseCaseImplTest {
    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `verify success` () = runBlocking {
        val mockCustomers = listOf(mockk<Customer>())
        val useCase = object : CustomersListUseCase {
            override suspend fun invoke(): CustomersListUseCaseResult = CustomersListUseCaseResult.Success(mockCustomers)
        }

        val result = useCase()

        assertEquals(CustomersListUseCaseResult.Success(mockCustomers), result)
    }

    @Test
    fun `verify error`() = runBlocking {
        val errorMessage = "An error occurred"
        val throwable = Throwable(errorMessage)
        val useCase = object : CustomersListUseCase {
            override suspend fun invoke(): CustomersListUseCaseResult = CustomersListUseCaseResult.Error(throwable)
        }

        val result = useCase()

        assertEquals(CustomersListUseCaseResult.Error(throwable), result)
    }
}