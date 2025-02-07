package com.example.testtaskandroid.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r")
    fun getOffers(): Call<GetOffersVacanciesResponse>

    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r")
    fun getVacancies(): Call<GetOffersVacanciesResponse>
}