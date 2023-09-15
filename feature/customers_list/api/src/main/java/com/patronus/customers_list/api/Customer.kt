package com.patronus.customers_list.api

data class Customer(
    val id : Int,
    val firstName : String,
    val lastName : String,
    val gender : String,
    val phoneNumber : String,
    val imageUrl : String?,
    val stickers : List<String>
)