package com.patronus.customers_list.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.patronus.common.AppTheme
import com.patronus.customers_list.presentation.ui.component.CustomerListScreen
import org.koin.android.ext.android.inject

class CustomersListActivity : AppCompatActivity() {

    private val customersListViewModel by inject<CustomersListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                CustomerListScreen(customersListViewModel)
            }
        }
    }
}