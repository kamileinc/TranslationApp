package com.example.translationapp.data.repository

import com.example.translationapp.data.api.TranslationApi
import com.example.translationapp.data.api.model.Translation
import javax.inject.Inject

class TranslationRepo @Inject constructor(private val translationApi: TranslationApi) {
    suspend fun getTranslation(translator: String, text: String) : Translation {
        return translationApi.getTranslation(translator, text)
    }
}
