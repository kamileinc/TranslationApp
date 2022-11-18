package com.example.translationapp.data.api

import com.example.translationapp.data.api.model.Translation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TranslationApi {
    @GET(ApiConstants.END_POINT)
    suspend fun getTranslation(
        @Path(value = "translator", encoded = true) translator: String,
        @Query("text") text: String
    ): Translation
}
