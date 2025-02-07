package com.example.testtaskandroid.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {

    private val httpClient by lazy {
        OkHttpClient()
            .newBuilder()
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/u/0/")
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val apiService: ApiService? by lazy {
        retrofit.create(ApiService::class.java)
    }
}