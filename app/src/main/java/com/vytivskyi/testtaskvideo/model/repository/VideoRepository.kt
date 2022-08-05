package com.vytivskyi.testtaskvideo.model.repository


import com.vytivskyi.testtaskvideo.data.MainVideos
import com.vytivskyi.testtaskvideo.data.Video
import retrofit2.Response

interface VideoRepository {

    suspend fun getVideos(): Response<MainVideos>

}