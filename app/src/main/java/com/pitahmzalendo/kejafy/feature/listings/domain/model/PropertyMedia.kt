package com.pitahmzalendo.kejafy.feature.listings.domain.model

sealed class PropertyMedia {
    data class Image(
        val url: String,
        val thumbnailUrl: String? = null
    ) : PropertyMedia()

    data class Video(
        val url: String,
        val thumbnailUrl: String? = null,
        val durationSeconds: Int? = null
    ) : PropertyMedia()
}
