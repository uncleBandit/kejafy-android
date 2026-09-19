package com.pitahmzalendo.kejafy.feature.listings.domain.model

data class SearchLocation(
    val latitude: Double,
    val longitude: Double,
    val radiusKm: Double? = null,
    val query: String? = null
)

data class ListingFilters(
    val listingType: ListingType? = null,
    val propertyTypes: Set<PropertyType> = emptySet(),
    val location: SearchLocation? = null,
    val minPrice: Long? = null,
    val maxPrice: Long? = null,
    val bedrooms: Int? = null,
    val bathrooms: Int? = null,
    val furnished: Boolean? = null
)
