package com.patronus.customers_list.presentation.ui

import androidx.lifecycle.viewModelScope
import com.patronus.common.BaseViewModel
import com.patronus.customers_list.api.usecase.CustomersListUseCase
import com.patronus.customers_list.api.usecase.CustomersListUseCaseResult
import com.patronus.customers_list.presentation.ui.data.CustomerUiModel
import com.patronus.customers_list.presentation.ui.data.toListUserUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomersListViewModel(val getCustomersListUseCase: CustomersListUseCase): BaseViewModel() {

    private val _customersListState = MutableStateFlow(CustomersListState())
    val customersList: StateFlow<CustomersListState> = _customersListState.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers() {
        fetchData()
    }

    fun retry() {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _customersListState.update { usersListState ->
                usersListState.copy(isLoading = true)
            }
            viewModelScope.launch {
                when(val customersListData = getCustomersListUseCase()) {
                    is CustomersListUseCaseResult.Success -> {
                        _customersListState.update { usersListState ->
                            usersListState.copy(isLoading = false, isError = false, errorMessage = null, customers = customersListData.users.toListUserUiModel())
                        }
                    }
                    is CustomersListUseCaseResult.Error -> {
                        _customersListState.update { usersListState ->
                            usersListState.copy(isLoading = false, isError = true, errorMessage = customersListData.throwable?.message)
                        }
                    }
                    else -> {
                        _customersListState.update { usersListState ->
                            usersListState.copy(isLoading = false, isError = true)
                        }
                    }
                }
            }
        }
    }
}

data class CustomersListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val customers: List<CustomerUiModel> = emptyList()
)


