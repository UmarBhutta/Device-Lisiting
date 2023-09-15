package com.patronus.customers_list.presentation.di

import com.patronus.customers_list.presentation.ui.CustomersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customersListModule = module {
    viewModel { CustomersListViewModel(get()) }
}