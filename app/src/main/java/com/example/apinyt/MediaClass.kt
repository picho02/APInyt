package com.example.apinyt

import com.google.gson.annotations.SerializedName

data class MediaClass(@SerializedName("media-metadata") val media_metadata: List<MediaData>)
