package com.example.giphloaderapp.network

import com.squareup.moshi.Json

data class GifResult(
    val result: List<GifProperty>
)

data class GifProperty(
    @Json(name = "gifURL") val imageUrl: String,
    val description: String
)