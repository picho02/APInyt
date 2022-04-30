package com.example.apinyt

import com.google.gson.annotations.SerializedName

data class Resultado(
    @SerializedName("url") val url: String,
    @SerializedName("title") var title: String,
    @SerializedName("published_date") val fecha: String,
    @SerializedName("media") val media: List<MediaClass>
)
