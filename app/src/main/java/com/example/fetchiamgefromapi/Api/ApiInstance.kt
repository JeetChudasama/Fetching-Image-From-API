package com.example.fetchiamgefromapi.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
    private const val Url = "https://api.thecatapi.com/"

    val api: ApiService by lazy{
        Retrofit.Builder()
            .baseUrl(Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}