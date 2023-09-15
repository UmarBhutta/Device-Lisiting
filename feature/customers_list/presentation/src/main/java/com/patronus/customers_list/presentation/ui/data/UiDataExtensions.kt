package com.patronus.customers_list.presentation.ui.data

import com.patronus.customers_list.api.Customer

fun Customer.toUiModel() : CustomerUiModel {
    return CustomerUiModel(
        id = this.id,
        initials = (this.firstName.take(1) + this.lastName.take(1)).uppercase(),
        name = "${this.firstName} ${this.lastName}",
        gender = this.gender.lowercase().replaceFirstChar { it.uppercase() },
        photoUrl = this.imageUrl,
        phoneNumber = this.phoneNumber,
        stickers = this.stickers
    )
}

fun List<Customer>.toListUserUiModel(): List<CustomerUiModel> {
    return this.map { it.toUiModel() }
}
