package com.patronus.customer_details.presentation.navigation

import android.content.Context
import android.content.Intent
import com.patronus.common.navigation.CustomerDetailsArgs
import com.patronus.common.navigation.CustomerDetailsNavigation
import com.patronus.customer_details.presentation.ui.CustomerDetailsActivity

internal const val ARG_KEY = "CustomerArgsKey"
class CustomerDetailsNavigationImpl: CustomerDetailsNavigation {
    override fun newIntent(context: Context, args: CustomerDetailsArgs): Intent =
        Intent(context, CustomerDetailsActivity::class.java).apply {
            putExtra(ARG_KEY, args)
        }
}