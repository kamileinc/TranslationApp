package com.example.translationapp.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contents(
    @Json(name = "text")
    val text: String,
    @Json(name = "translated")
    val translated: String,
    @Json(name = "translation")
    val translation: String
)
