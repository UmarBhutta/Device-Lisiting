package com.patronus.network

import com.patronus.network.model.CustomerDetailsDto
import com.patronus.network.model.CustomersObject
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users")
    suspend fun fetchCustomersList(): CustomersObject

    @GET("/users/{id}")
    suspend fun fetchCustomerDetails(@Path("id") id:Int): CustomerDetailsDto
}