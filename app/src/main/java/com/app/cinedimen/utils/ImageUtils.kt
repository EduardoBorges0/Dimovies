package com.app.cinedimen.utils

object ImageUtils {
    private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"

    fun getImageUrl(path: String?, size: String = "original"): String {
        return if (!path.isNullOrEmpty()) "$BASE_IMAGE_URL$size$path" else ""
    }
}