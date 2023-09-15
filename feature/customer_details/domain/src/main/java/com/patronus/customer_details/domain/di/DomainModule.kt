package com.patronus.customer_details.domain.di

import com.patronus.customer_details.api.repo.CustomerDetailsRepository
import com.patronus.customer_details.api.usecase.CustomerDetailsUseCase
import com.patronus.customer_details.domain.repo.CustomerDetailsRepositoryImpl
import com.patronus.customer_details.domain.usecase.CustomerDetailsUseCaseImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val customerDetailsDomainModule = module {
    single<CustomerDetailsRepository> { CustomerDetailsRepositoryImpl(get()) }
    single<CustomerDetailsUseCase> { CustomerDetailsUseCaseImpl(coroutineContext = Dispatchers.IO, get()) }
}