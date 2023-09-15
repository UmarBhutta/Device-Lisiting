package com.patronus.api

data class CustomerDetails(
    val id : Int,
    val imageUrl : String,
    val currentLatitude : Double,
    val currentLongitude : Double,
    val firstName : String,
    val lastName : String,
    val stickers : List<String>,
    val gender : String,
    val phoneNumber : String,
    val address : Address
){
    data class Address (

        val street : String,
        val city : String,
        val state : String,
        val zip : Int,
        val country : String
    )
}