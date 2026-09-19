package com.pitahmzalendo.kejafy.feature.listings.data.remote.dto

data class ListingAmenityDto(
    val id: Long,
    val type: String,
    val name: String?,
    val distanceMeters: Int?,
    val category: String
)
