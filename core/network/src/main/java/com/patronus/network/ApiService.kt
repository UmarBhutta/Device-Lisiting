package com.patronus.network

import com.patronus.network.model.CustomerDetailsDto
import com.patronus.network.model.CustomerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/users")
    suspend fun fetchCustomersList(): List<CustomerDto>

    @GET("/users")
    suspend fun fetchCustomerDetails(@Query("id") id:Int): CustomerDetailsDto
}