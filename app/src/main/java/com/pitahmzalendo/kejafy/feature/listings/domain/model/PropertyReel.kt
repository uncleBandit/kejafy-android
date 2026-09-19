package com.pitahmzalendo.kejafy.feature.listings.domain.model

data class PropertyReel(
    val id: Long,
    val propertyId: Long,
    val videoUrl: String,
    val thumbnailUrl: String?,
    val durationSeconds: Int
)
