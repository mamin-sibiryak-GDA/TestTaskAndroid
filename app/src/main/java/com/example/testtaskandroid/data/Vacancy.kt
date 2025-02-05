package com.example.testtaskandroid.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Vacancy(
    @Json(name = "address")
    val address: Address?,
    @Json(name = "appliedNumber")
    val appliedNumber: Int?,
    @Json(name = "company")
    val company: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "experience")
    val experience: Experience?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "isFavorite")
    val isFavorite: Boolean?,
    @Json(name = "lookingNumber")
    val lookingNumber: Int?,
    @Json(name = "publishedDate")
    val publishedDate: String?,
    @Json(name = "questions")
    val questions: List<String?>?,
    @Json(name = "responsibilities")
    val responsibilities: String?,
    @Json(name = "salary")
    val salary: Salary?,
    @Json(name = "schedules")
    val schedules: List<String?>?,
    @Json(name = "title")
    val title: String?
)