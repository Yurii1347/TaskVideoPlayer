package com.vytivskyi.testtaskvideo.model.repository

import com.vytivskyi.testtaskvideo.data.MainVideos
import com.yurii.vytivskyi.trainingapp.model.APIs.ApiInterface
import retrofit2.Response

class VideoRepositoryImpl: VideoRepository {
    private val apiInterface = ApiInterface.create()

    override suspend fun getVideos(): Response<MainVideos> {
       return apiInterface.getVideos()
    }




}