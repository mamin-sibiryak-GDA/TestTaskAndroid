package com.example.testtaskandroid.data.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Salary(
    @Json(name = "full")
    val full: String,
    @Json(name = "short")
    val short: String?
)