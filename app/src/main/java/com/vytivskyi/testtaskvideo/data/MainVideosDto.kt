package com.vytivskyi.testtaskvideo.data

import com.google.gson.annotations.SerializedName

data class MainVideosDto(
    @SerializedName("categories")
    val categories: List<CategoryDto>
)