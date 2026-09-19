package com.pitahmzalendo.kejafy.feature.listings.data.remote.dto

data class ListingVideoDto(
    val id: Long,
    val videoUrl: String,
    val thumbnailUrl: String?,
    val durationSeconds: Int?,
    val displayOrder: Int
)
