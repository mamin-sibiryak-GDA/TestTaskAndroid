package com.example.testtaskandroid.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r")
    fun getOffers(): Call<GetOffersVacanciesResponse>

    fun getVacancies(): Call<GetOffersVacanciesResponse>
}