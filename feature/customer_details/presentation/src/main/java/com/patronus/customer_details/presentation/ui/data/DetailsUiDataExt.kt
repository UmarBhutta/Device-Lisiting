package com.patronus.customer_details.presentation.ui.data

import com.patronus.api.CustomerDetails

fun CustomerDetails.toCustomerDetailUiModel(): CustomerDetailUiModel {
    return CustomerDetailUiModel(
        initials = (this.firstName.take(1) + this.lastName.take(1)).uppercase(),
        name = "${this.firstName} ${this.lastName}",
        gender = this.gender.lowercase().replaceFirstChar { it.uppercase() },
        photoUrl = this.imageUrl,
        phoneNumber = this.phoneNumber,
        stickers = this.stickers,
        currentLatitude = this.currentLatitude,
        currentLongitude = this.currentLongitude,
        address = "${this.address.street}, ${this.address.zip} ${this.address.city}"
    )
}