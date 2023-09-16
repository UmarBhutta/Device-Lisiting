package com.patronus.customer_details.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.patronus.common.navigation.CustomerDetailsArgs
import com.patronus.common.theme.AppTheme
import com.patronus.customer_details.presentation.navigation.ARG_KEY
import com.patronus.customer_details.presentation.ui.component.CustomerDetailsScreen
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CustomerDetailsActivity : ComponentActivity() {

    private val args = intent.getParcelableExtra(ARG_KEY, CustomerDetailsArgs::class.java)

    private val customerDetailsViewModel by inject<CustomerDetailsViewModel> {
        parametersOf(args?.customerId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                CustomerDetailsScreen(customerDetailsViewModel) {
                    onBackPressed()
                }
            }
        }
    }
}