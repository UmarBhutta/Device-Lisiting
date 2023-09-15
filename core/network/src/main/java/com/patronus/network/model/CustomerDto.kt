package com.patronus.network.model

import com.google.gson.annotations.SerializedName

data class CustomerDto(
    @SerializedName("id") val id : Int,
    @SerializedName("firstName") val firstName : String,
    @SerializedName("lastName") val lastName : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("phoneNumber") val phoneNumber : String,
    @SerializedName("imageUrl") val imageUrl : String,
    @SerializedName("stickers") val stickers : List<String>
)
