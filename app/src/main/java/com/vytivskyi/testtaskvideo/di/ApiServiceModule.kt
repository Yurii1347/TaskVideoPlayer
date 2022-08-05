package com.vytivskyi.testtaskvideo.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vytivskyi.testtaskvideo.model.service.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiServiceModule {
    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/"
    }

    @Provides
    fun provideApiInterface(gson: Gson): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }
}