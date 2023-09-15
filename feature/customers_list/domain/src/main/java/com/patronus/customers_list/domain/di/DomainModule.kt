package com.patronus.customers_list.domain.di


import com.patronus.customers_list.api.repo.CustomersListRepository
import com.patronus.customers_list.api.usecase.CustomersListUseCase
import com.patronus.customers_list.domain.repo.CustomersListRepositoryImpl
import com.patronus.customers_list.domain.usecase.CustomersListUseCaseImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val customersListDomainModule = module {
    single<CustomersListRepository> { CustomersListRepositoryImpl(get()) }
    single<CustomersListUseCase> { CustomersListUseCaseImpl(coroutineContext = Dispatchers.IO, get()) }
}