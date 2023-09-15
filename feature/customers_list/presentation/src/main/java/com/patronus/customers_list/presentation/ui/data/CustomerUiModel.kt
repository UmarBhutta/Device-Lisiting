package com.patronus.customers_list.presentation.ui.data

data class CustomerUiModel(
    val id: Int,
    val name: String,
    val initials: String,
    val gender: String,
    val photoUrl: String?,
    val stickers: List<String>,
    val phoneNumber: String
)
