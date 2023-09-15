package com.patronus.customer_details.domain.extension

import com.patronus.api.CustomerDetails
import com.patronus.network.model.CustomerDetailsDto

fun CustomerDetailsDto.toCustomerDetails(): CustomerDetails {
    return CustomerDetails(
        id = this.id,
        imageUrl = this.imageUrl,
        currentLatitude = this.currentLatitude,
        currentLongitude = this.currentLongitude,
        firstName = this.firstName,
        lastName = this.lastName,
        stickers = this.stickers,
        gender = this.gender,
        phoneNumber = this.phoneNumber,
        address = CustomerDetails.Address(
            street = this.address.street,
            city = this.address.city,
            state = this.address.state,
            zip = this.address.zip,
            country = this.address.country
        )
    )
}
