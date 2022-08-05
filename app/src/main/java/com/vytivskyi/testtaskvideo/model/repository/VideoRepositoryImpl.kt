package com.vytivskyi.testtaskvideo.model.repository

import com.vytivskyi.testtaskvideo.data.MainVideos
import com.vytivskyi.testtaskvideo.model.service.ApiInterface
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getVideos(): Result<MainVideos> {
        return runCatching { apiInterface.getVideos() }
    }
}