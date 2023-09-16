package com.patronus.customers_list.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.patronus.common.navigation.CustomerDetailsArgs
import com.patronus.common.navigation.CustomerDetailsNavigation
import com.patronus.common.theme.AppTheme
import com.patronus.customers_list.presentation.ui.component.CustomerListScreen
import org.koin.android.ext.android.inject

class CustomersListActivity : ComponentActivity() {

    private val customersListViewModel by inject<CustomersListViewModel>()

    private val customerDetailsNavigation: CustomerDetailsNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                CustomerListScreen(customersListViewModel){
                    navigateToDetailsScreen(it)
                }
            }
        }
    }

    private fun navigateToDetailsScreen(customerId: Int) {
        startActivity(customerDetailsNavigation.newIntent(this, CustomerDetailsArgs(customerId)))
    }
}