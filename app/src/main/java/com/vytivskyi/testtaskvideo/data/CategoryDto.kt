package com.vytivskyi.testtaskvideo.data

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("videos")
    val videos: List<VideoDto>
)