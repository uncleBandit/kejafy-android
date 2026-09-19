package com.pitahmzalendo.kejafy.feature.listings.domain.model

data class PropertyLocation(
    val latitude: Double,
    val longitude: Double,
    val address: String? = null,
    val neighbourhood: String? = null,
    val city: String? = null,
    val country: String? = null
)
