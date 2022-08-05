package com.vytivskyi.testtaskvideo.model.repository

import androidx.lifecycle.LiveData
import com.vytivskyi.testtaskvideo.data.db.AppDatabase
import com.vytivskyi.testtaskvideo.data.db.VideoEntity
import com.vytivskyi.testtaskvideo.model.service.ApiInterface
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val appDatabase: AppDatabase,
) {

    fun observeVideos(): LiveData<List<VideoEntity>> {
        return appDatabase.videoDao().observeAll()
    }

    suspend fun fetchVideos() {
        runCatching { apiInterface.getVideos() }
            .onSuccess { videosDto ->
                appDatabase.videoDao().putVideos(
                    videosDto.categories
                        .map { it.videos }
                        .flatten()
                        .map {
                            VideoEntity(
                                uid = it.title.hashCode(),
                                source = it.sources.first(),
                                title = it.title,
                                subtitle = it.subtitle,
                                description = it.description,
                            )
                        })
            }
    }
}