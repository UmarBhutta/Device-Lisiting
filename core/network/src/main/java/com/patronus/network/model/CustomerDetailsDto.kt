package com.patronus.network.model

import com.google.gson.annotations.SerializedName

data class CustomerDetailsDto(
    @SerializedName("id") val id : Int,
    @SerializedName("imageUrl") val imageUrl : String,
    @SerializedName("currentLatitude") val currentLatitude : Double,
    @SerializedName("currentLongitude") val currentLongitude : Double,
    @SerializedName("firstName") val firstName : String,
    @SerializedName("lastName") val lastName : String,
    @SerializedName("stickers") val stickers : List<String>,
    @SerializedName("gender") val gender : String,
    @SerializedName("phoneNumber") val phoneNumber : String,
    @SerializedName("address") val address : Address
)

data class Address (

    @SerializedName("street") val street : String,
    @SerializedName("city") val city : String,
    @SerializedName("state") val state : String,
    @SerializedName("zip") val zip : Int,
    @SerializedName("country") val country : String
)