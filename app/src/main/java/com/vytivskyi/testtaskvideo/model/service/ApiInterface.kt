package com.vytivskyi.testtaskvideo.model.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vytivskyi.testtaskvideo.data.MainVideos
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("Yurii1347/videoData/main/Data")
    suspend fun getVideos(): MainVideos



}

