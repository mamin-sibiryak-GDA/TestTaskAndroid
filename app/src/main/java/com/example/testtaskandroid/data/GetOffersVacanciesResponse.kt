package com.example.testtaskandroid.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetOffersVacanciesResponse(
    @Json(name = "offers")
    val offers: List<Offer>,
    @Json(name = "vacancies")
    val vacancies: List<Vacancy>
)