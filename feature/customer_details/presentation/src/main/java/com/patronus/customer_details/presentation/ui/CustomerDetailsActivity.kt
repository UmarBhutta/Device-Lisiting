package com.patronus.customer_details.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.patronus.common.AppTheme
import com.patronus.customer_details.presentation.ui.component.CustomerDetailsScreen

class CustomerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                CustomerDetailsScreen()
            }
        }
    }
}