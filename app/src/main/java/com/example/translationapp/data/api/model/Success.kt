package com.example.translationapp.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Success(
    @Json(name = "total")
    val total: Int
)
