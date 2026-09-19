package com.pitahmzalendo.kejafy.feature.listings.domain.model

data class PropertyListing(
    val id: Long,
    val title: String,
    val description: String?,
    val listingType: ListingType,
    val propertyType: PropertyType,
    val price: Money,
    val location: PropertyLocation,
    val bedrooms: Int?,
    val bathrooms: Int?,
    val amenities: List<Amenity>,
    val media: List<PropertyMedia>,
    val environmentScore: EnvironmentScore?,
    val agent: AgentSummary?,
    val isLocked: Boolean = false
)
