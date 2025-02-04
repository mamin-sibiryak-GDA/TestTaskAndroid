package com.example.testtaskandroid.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Offer(
    @Json(name = "button")
    val button: Button,
    @Json(name = "id")
    val id: String,
    @Json(name = "link")
    val link: String,
    @Json(name = "title")
    val title: String
)