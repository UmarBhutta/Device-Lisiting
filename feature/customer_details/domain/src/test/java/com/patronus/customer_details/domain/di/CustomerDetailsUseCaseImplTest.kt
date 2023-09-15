package com.patronus.customer_details.domain.di

import com.patronus.api.CustomerDetails
import com.patronus.customer_details.api.usecase.CustomerDetailsUseCase
import com.patronus.customer_details.api.usecase.CustomerDetailsUseCaseResult
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CustomerDetailsUseCaseImplTest {
    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `verify success` () = runBlocking {
        val id = 1
        val mockCustomer = mockk<CustomerDetails>()
        val useCase = object : CustomerDetailsUseCase {
            override suspend fun invoke(customerId: Int): CustomerDetailsUseCaseResult  = CustomerDetailsUseCaseResult.Success(mockCustomer)
        }

        val result = useCase(id)

        Assert.assertEquals(CustomerDetailsUseCaseResult.Success(mockCustomer), result)
    }

    @Test
    fun `verify error`() = runBlocking {
        val errorMessage = "An error occurred"
        val id = 1
        val throwable = Throwable(errorMessage)
        val useCase = object : CustomerDetailsUseCase {
            override suspend fun invoke(customerId: Int): CustomerDetailsUseCaseResult = CustomerDetailsUseCaseResult.Error(throwable)
        }

        val result = useCase(id)

        Assert.assertEquals(CustomerDetailsUseCaseResult.Error(throwable), result)
    }
}