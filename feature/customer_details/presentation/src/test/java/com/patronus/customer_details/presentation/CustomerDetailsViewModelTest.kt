package com.patronus.customer_details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.patronus.api.CustomerDetails
import com.patronus.customer_details.api.usecase.CustomerDetailsUseCase
import com.patronus.customer_details.api.usecase.CustomerDetailsUseCaseResult
import com.patronus.customer_details.presentation.ui.CustomerDetailsState
import com.patronus.customer_details.presentation.ui.CustomerDetailsViewModel
import com.patronus.customer_details.presentation.ui.data.toCustomerDetailUiModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class CustomerDetailsViewModelTest {
    private lateinit var getCustomerDetailsUseCase: CustomerDetailsUseCase

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()
    
    private val customerId = 1

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        getCustomerDetailsUseCase = mockk<CustomerDetailsUseCase>()
    }

    @Test
    fun `verify details is loaded`() = runTest {
        val customer = mockk<CustomerDetails>(relaxed = true)
        coEvery { getCustomerDetailsUseCase(customerId) } returns CustomerDetailsUseCaseResult.Success(
            customer
        )
        val viewModel = CustomerDetailsViewModel(customerId,getCustomerDetailsUseCase)

        viewModel.customerDetails.test {
            assertEquals(CustomerDetailsState(customer = customer.toCustomerDetailUiModel()), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify data is loaded and mapped to UI Model`() = runTest {
        val customer = mockk<CustomerDetails>(relaxed = true)
        coEvery { getCustomerDetailsUseCase(customerId) } returns CustomerDetailsUseCaseResult.Success(
            customer
        )
        val viewModel = CustomerDetailsViewModel(customerId,getCustomerDetailsUseCase)


        viewModel.customerDetails.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(false, state.isError)
            assertEquals(null, state.errorMessage)
            assertEquals(customer.toCustomerDetailUiModel(), state.customer)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify error is happened while loading`() = runTest {
        val errorMessage = "An error occurred"
        coEvery { getCustomerDetailsUseCase(customerId) } returns CustomerDetailsUseCaseResult.Error(Throwable(errorMessage))
        val viewModel = CustomerDetailsViewModel(customerId,getCustomerDetailsUseCase)

        viewModel.customerDetails.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(true, state.isError)
            assertEquals(errorMessage, state.errorMessage)
            assertNull(state.customer)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify error is happened while loading and retry success `() = runTest {
        val errorMessage = "An error occurred"
        coEvery { getCustomerDetailsUseCase(customerId) } returns CustomerDetailsUseCaseResult.Error(Throwable(errorMessage))
        val viewModel = CustomerDetailsViewModel(customerId,getCustomerDetailsUseCase)

        viewModel.customerDetails.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(true, state.isError)
            assertEquals(errorMessage, state.errorMessage)
            assertNull(state.customer)
            cancelAndConsumeRemainingEvents()
        }

        val customer = mockk<CustomerDetails>(relaxed = true)
        coEvery { getCustomerDetailsUseCase(customerId) } returns CustomerDetailsUseCaseResult.Success(
            customer
        )

        viewModel.retry()

        viewModel.customerDetails.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(false, state.isError)
            assertEquals(null, state.errorMessage)
            assertEquals(customer.toCustomerDetailUiModel(), state.customer)
            cancelAndConsumeRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}