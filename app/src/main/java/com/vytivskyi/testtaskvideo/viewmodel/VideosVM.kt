package com.vytivskyi.testtaskvideo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vytivskyi.testtaskvideo.data.Video
import com.vytivskyi.testtaskvideo.model.repository.VideoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosVM @Inject constructor(
    private val mVideosRepository: VideoRepositoryImpl
) : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> = _videos

    init {
        viewModelScope.launch {
            getVideos()
        }
    }

    private suspend fun getVideos() {
        mVideosRepository.getVideos()
            .onSuccess { mainVideos ->
                _videos.postValue(mainVideos.categories.map { it.videos }.flatten())
            }
            .onFailure {
                Log.e("HUI", "HUI", it)
            }

    }
}