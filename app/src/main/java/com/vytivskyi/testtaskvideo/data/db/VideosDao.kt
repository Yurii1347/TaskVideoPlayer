package com.vytivskyi.testtaskvideo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VideoDao {
    @Query("SELECT * FROM VideoEntity")
    fun observeAll(): LiveData<List<VideoEntity>>

    @Insert
    suspend fun putVideos(videos: List<VideoEntity>)
}