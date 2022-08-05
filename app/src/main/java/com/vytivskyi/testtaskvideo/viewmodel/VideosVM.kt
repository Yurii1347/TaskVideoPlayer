package com.vytivskyi.testtaskvideo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vytivskyi.testtaskvideo.data.Video
import com.vytivskyi.testtaskvideo.model.repository.VideoRepository
import com.vytivskyi.testtaskvideo.model.repository.VideoRepositoryImpl

class VideosVM {
    private val mVideosRepository: VideoRepository = VideoRepositoryImpl()

    private val _videos = MutableLiveData<List<Video>?>()
    val videos : LiveData<List<Video>?> = _videos

    suspend fun getVideos() {
        val result = mVideosRepository.getVideos()
        _videos.postValue(result.body()?.categories?.get(0)?.videos)
    }
}