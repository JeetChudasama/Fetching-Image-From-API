package com.example.fetchiamgefromapi.Api

import retrofit2.http.GET

interface ApiService {
    @GET("v1/images/search")
    suspend fun getImage(): List<ApiResponse>
}