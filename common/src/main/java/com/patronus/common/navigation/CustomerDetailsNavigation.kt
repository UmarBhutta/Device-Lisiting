package com.patronus.common.navigation

import kotlinx.parcelize.Parcelize

interface CustomerDetailsNavigation: Navigation<CustomerDetailsArgs>

@Parcelize
class CustomerDetailsArgs(
    val customerId: Int
) : Arguments