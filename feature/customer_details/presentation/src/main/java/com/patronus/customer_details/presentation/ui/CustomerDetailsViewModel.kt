package com.patronus.customer_details.presentation.ui

import androidx.lifecycle.viewModelScope
import com.patronus.common.BaseViewModel
import com.patronus.customer_details.api.usecase.CustomerDetailsUseCase
import com.patronus.customer_details.api.usecase.CustomerDetailsUseCaseResult
import com.patronus.customer_details.presentation.ui.data.CustomerDetailUiModel
import com.patronus.customer_details.presentation.ui.data.toCustomerDetailUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerDetailsViewModel(
    val customerId: Int,
    val getCustomerDetails: CustomerDetailsUseCase
): BaseViewModel(){
    
    private val _customerDetailsState = MutableStateFlow(CustomerDetailsState())
    val customerDetails: StateFlow<CustomerDetailsState> = _customerDetailsState.asStateFlow()

    init {
        loadUsers(customerId)
    }

    fun loadUsers(customerId: Int) {
        fetchData(customerId)
    }

    fun retry() {
        fetchData(customerId)
    }

    private fun fetchData(customerId: Int) {
        viewModelScope.launch {
            _customerDetailsState.update { customerDetailsState ->
                customerDetailsState.copy(isLoading = true)
            }
            viewModelScope.launch {
                when(val customerData = getCustomerDetails(customerId)) {
                    is CustomerDetailsUseCaseResult.Success -> {
                        _customerDetailsState.update { customerDetailsState ->
                            customerDetailsState.copy(isLoading = false, isError = false, errorMessage = null, customer = customerData.customerDetails.toCustomerDetailUiModel())
                        }
                    }
                    is CustomerDetailsUseCaseResult.Error -> {
                        _customerDetailsState.update { customerDetailsState ->
                            customerDetailsState.copy(isLoading = false, isError = true, errorMessage = customerData.throwable?.message, customer = null)
                        }
                    }
                    else -> {
                        _customerDetailsState.update { customerDetailsState ->
                            customerDetailsState.copy(isLoading = false, isError = true)
                        }
                    }
                }
            }
        }
    }
}


data class CustomerDetailsState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val customer: CustomerDetailUiModel? = null
)