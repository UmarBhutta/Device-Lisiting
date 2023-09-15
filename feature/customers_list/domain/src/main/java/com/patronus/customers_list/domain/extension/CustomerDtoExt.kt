package com.patronus.customers_list.domain.extension

import com.patronus.customers_list.api.Customer
import com.patronus.network.model.CustomerDto

fun CustomerDto.toCustomer(): Customer{
    return Customer(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        gender = this.gender,
        phoneNumber = this.phoneNumber,
        imageUrl = this.imageUrl,
        stickers = this.stickers
    )
}

fun List<CustomerDto>.toCustomers() = this.map { it.toCustomer() }