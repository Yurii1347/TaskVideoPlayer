package com.vytivskyi.testtaskvideo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.vytivskyi.testtaskvideo.model.Video
import com.vytivskyi.testtaskvideo.model.repository.VideoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosVM @Inject constructor(
    private val mVideosRepository: VideoRepositoryImpl
) : ViewModel() {

    fun observeVideos(): LiveData<List<Video>> {
        return mVideosRepository.observeVideos()
            .map { list ->
                list.map {
                    Video(
                        uid = it.uid,
                        source = it.source,
                        title = it.title,
                        subtitle = it.subtitle,
                        description = it.description,
                    )
                }
            }
    }

    fun fetchVideos() {
        viewModelScope.launch {
            mVideosRepository.fetchVideos()
        }
    }
}
