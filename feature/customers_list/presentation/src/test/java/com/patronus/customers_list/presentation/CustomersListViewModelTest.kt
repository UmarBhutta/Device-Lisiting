package com.patronus.customers_list.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.patronus.customers_list.api.Customer
import com.patronus.customers_list.api.usecase.CustomersListUseCase
import com.patronus.customers_list.api.usecase.CustomersListUseCaseResult
import com.patronus.customers_list.presentation.ui.CustomersListState
import com.patronus.customers_list.presentation.ui.CustomersListViewModel
import com.patronus.customers_list.presentation.ui.data.toListUserUiModel
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
class CustomersListViewModelTest {

    private lateinit var getCustomersListUseCase: CustomersListUseCase

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        getCustomersListUseCase = mockk<CustomersListUseCase>()
    }

    @Test
    fun `verify list is loaded`() = runTest {
        coEvery { getCustomersListUseCase() } returns CustomersListUseCaseResult.Success(users = emptyList())
        val viewModel = CustomersListViewModel(getCustomersListUseCase)

        viewModel.customersList.test {
            assertEquals(CustomersListState(), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify data is loaded and mapped to UI Model`() = runTest {
        val customers = listOf<Customer>(
            mockk(relaxed = true)
        )
        coEvery { getCustomersListUseCase() } returns CustomersListUseCaseResult.Success(
            users =  customers
        )
        val viewModel = CustomersListViewModel(getCustomersListUseCase)


        viewModel.customersList.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(false, state.isError)
            assertEquals(null, state.errorMessage)
            assertEquals(customers.toListUserUiModel(), state.customers)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify error is happened while loading`() = runTest {
        val errorMessage = "An error occurred"
        coEvery { getCustomersListUseCase() } returns CustomersListUseCaseResult.Error(Throwable(errorMessage))
        val viewModel = CustomersListViewModel(getCustomersListUseCase)

        viewModel.customersList.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(true, state.isError)
            assertEquals(errorMessage, state.errorMessage)
            assertTrue(state.customers.isEmpty())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `verify error is happened while loading and retry success `() = runTest {
        val errorMessage = "An error occurred"
        coEvery { getCustomersListUseCase() } returns CustomersListUseCaseResult.Error(Throwable(errorMessage))
        val viewModel = CustomersListViewModel(getCustomersListUseCase)

        viewModel.customersList.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(true, state.isError)
            assertEquals(errorMessage, state.errorMessage)
            assertTrue(state.customers.isEmpty())
            cancelAndConsumeRemainingEvents()
        }

        val customers = listOf<Customer>(
            mockk(relaxed = true)
        )
        coEvery { getCustomersListUseCase() } returns CustomersListUseCaseResult.Success(
            users =  customers
        )

        viewModel.retry()

        viewModel.customersList.test {
            val state = awaitItem()
            assertEquals(false, state.isLoading)
            assertEquals(false, state.isError)
            assertEquals(null, state.errorMessage)
            assertEquals(customers.toListUserUiModel(), state.customers)
            cancelAndConsumeRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}