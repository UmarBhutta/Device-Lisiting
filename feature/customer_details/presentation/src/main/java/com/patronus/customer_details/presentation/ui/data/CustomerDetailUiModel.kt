package com.patronus.customer_details.presentation.ui.data

data class CustomerDetailUiModel(
    val name: String,
    val initials: String,
    val gender: String,
    val photoUrl: String?,
    val stickers: List<String>,
    val phoneNumber: String,
    val address: String,
    val currentLatitude : Double,
    val currentLongitude : Double,
)