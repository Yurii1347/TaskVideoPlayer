package com.vytivskyi.testtaskvideo.model.service

import com.vytivskyi.testtaskvideo.data.MainVideosDto
import retrofit2.http.GET

interface ApiInterface {
    @GET("Yurii1347/videoData/main/Data")
    suspend fun getVideos(): MainVideosDto
}

