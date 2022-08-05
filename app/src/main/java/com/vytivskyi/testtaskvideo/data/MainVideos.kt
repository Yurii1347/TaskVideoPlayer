package com.vytivskyi.testtaskvideo.data

import com.google.gson.annotations.SerializedName

data class MainVideos(
    @SerializedName("categories")
    val categories: List<Category>
)