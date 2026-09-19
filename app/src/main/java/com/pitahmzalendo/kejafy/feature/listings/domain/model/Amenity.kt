package com.pitahmzalendo.kejafy.feature.listings.domain.model

enum class AmenityCategory {
    EDUCATION,
    HEALTHCARE,
    TRANSPORT,
    SHOPPING,
    SECURITY,
    RECREATION,
    FINANCIAL,
    DINING,
    WORSHIP,
    FUEL,
    OTHER
}

data class Amenity(
    val id: Long,
    val name: String,
    val category: AmenityCategory,
    val distanceMeters: Int? = null
)
