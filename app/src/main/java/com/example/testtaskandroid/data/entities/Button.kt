package com.example.testtaskandroid.data.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Button(
    @Json(name = "text")
    val text: String
)