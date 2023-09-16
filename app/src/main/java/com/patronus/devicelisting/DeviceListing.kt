package com.patronus.devicelisting

import android.app.Application
import com.patronus.customer_details.domain.di.customerDetailsDomainModule
import com.patronus.customer_details.presentation.di.customerDetailsModule
import com.patronus.customers_list.domain.di.customersListDomainModule
import com.patronus.customers_list.presentation.di.customersListModule
import com.patronus.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DeviceListing: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DeviceListing)
            modules(
                listOf(
                    networkModule,
                    customersListDomainModule,
                    customersListModule,
                    customerDetailsDomainModule,
                    customerDetailsModule
                )
            )
        }
    }
}