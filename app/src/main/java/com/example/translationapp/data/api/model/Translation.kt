package com.example.translationapp.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Translation(
    @Json(name = "contents")
    val contents: Contents,
    @Json(name = "success")
    val success: Success
)
