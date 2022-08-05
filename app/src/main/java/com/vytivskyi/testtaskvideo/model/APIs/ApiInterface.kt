package com.yurii.vytivskyi.trainingapp.model.APIs

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vytivskyi.testtaskvideo.data.MainVideos
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET ("Yurii1347/videoData/main/Data")
    suspend fun getVideos() : Response<MainVideos>

    companion object {

        private const val BASE_URL = "https://raw.githubusercontent.com/"

        var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

    }

}

