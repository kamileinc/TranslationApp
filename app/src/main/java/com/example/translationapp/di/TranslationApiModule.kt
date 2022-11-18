package com.example.translationapp.di

import com.example.translationapp.data.api.ApiConstants
import com.example.translationapp.data.api.TranslationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TranslationApiModule {

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder) : TranslationApi {
        return builder
            .build()
            .create(TranslationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}
