package com.patronus.customer_details.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.patronus.common.theme.AppTheme
import com.patronus.customer_details.presentation.ui.component.CustomerDetailsScreen

class CustomerDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                CustomerDetailsScreen()
            }
        }
    }
}