package com.patronus.customer_details.presentation.di

import com.patronus.customer_details.presentation.ui.CustomerDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerDetailsModule = module {
    viewModel{ params -> CustomerDetailsViewModel(params.get(), get()) }
}