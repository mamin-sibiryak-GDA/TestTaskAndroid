package com.example.testtaskandroid.data.remote


import com.example.testtaskandroid.data.entities.Offer
import com.example.testtaskandroid.data.entities.Vacancy
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetOffersVacanciesResponse(
    @Json(name = "offers")
    val offers: List<Offer>,
    @Json(name = "vacancies")
    val vacancies: List<Vacancy>
)