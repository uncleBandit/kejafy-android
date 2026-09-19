package com.pitahmzalendo.kejafy.feature.listings.data.mapper

import com.pitahmzalendo.kejafy.feature.listings.data.remote.dto.*
import com.pitahmzalendo.kejafy.feature.listings.domain.model.*
import java.math.BigDecimal

fun ListingDetailDto.toDomain(): PropertyListing {
    return PropertyListing(
        id = id,
        title = title,
        description = description,
        listingType = ListingType.valueOf(purpose.replace("FOR_", "")),
        propertyType = PropertyType.valueOf(propertyType),
        price = Money(priceAmount, priceCurrency),
        location = PropertyLocation(
            latitude = latitude ?: 0.0,
            longitude = longitude ?: 0.0,
            neighbourhood = neighbourhood
        ),
        bedrooms = bedrooms,
        bathrooms = bathrooms,
        amenities = amenities.map { it.toDomain() },
        media = videos.map { it.toDomain() },
        environmentScore = environmentProfile?.toDomain(),
        agent = AgentSummary(
            id = agentId,
            name = agentName,
            phoneNumber = agentPhone
        )
    )
}

fun ListingFeedItemDto.toDomain(): PropertyListing {
    return PropertyListing(
        id = id,
        title = title ?: "Locked Listing",
        description = description,
        listingType = purpose?.let { ListingType.valueOf(it.replace("FOR_", "")) } ?: ListingType.RENT,
        propertyType = propertyType?.let { PropertyType.valueOf(it) } ?: PropertyType.APARTMENT,
        price = Money(priceAmount ?: BigDecimal.ZERO, priceCurrency ?: "KES"),
        location = PropertyLocation(
            latitude = latitude ?: 0.0,
            longitude = longitude ?: 0.0,
            neighbourhood = neighbourhood
        ),
        bedrooms = bedrooms,
        bathrooms = bathrooms,
        amenities = amenities.map { it.toDomain() },
        media = videos.map { it.toDomain() },
        environmentScore = environmentProfile?.toDomain(),
        agent = null,
        isLocked = locked
    )
}

fun ListingVideoDto.toDomain(): PropertyMedia.Video {
    return PropertyMedia.Video(
        url = videoUrl,
        thumbnailUrl = thumbnailUrl,
        durationSeconds = durationSeconds
    )
}

fun ListingAmenityDto.toDomain(): Amenity {
    return Amenity(
        id = id,
        name = name ?: type,
        category = AmenityCategory.valueOf(category),
        distanceMeters = distanceMeters
    )
}

fun EnvironmentProfileDto.toDomain(): EnvironmentScore {
    return EnvironmentScore(
        overall = overallScore,
        noise = noiseLevel?.let { FactorScore(it, it.toRiskLevel()) },
        floodRisk = floodRiskLevel?.let { FactorScore(it, it.toRiskLevel()) },
        security = securityLevel?.let { FactorScore(it, it.toQualityRiskLevel()) },
        utilities = waterReliability?.let { FactorScore(it, it.toQualityRiskLevel()) }
    )
}

private fun Int.toRiskLevel(): RiskLevel = when (this) {
    1, 2 -> RiskLevel.LOW
    3 -> RiskLevel.MEDIUM
    4, 5 -> RiskLevel.HIGH
    else -> RiskLevel.UNKNOWN
}

private fun Int.toQualityRiskLevel(): RiskLevel = when (this) {
    1, 2 -> RiskLevel.HIGH
    3 -> RiskLevel.MEDIUM
    4, 5 -> RiskLevel.LOW
    else -> RiskLevel.UNKNOWN
}

fun <T, R> PagedResponseDto<T>.toDomain(mapper: (T) -> R): PagedResult<R> {
    return PagedResult(
        data = data.map(mapper),
        nextCursor = nextCursor,
        hasMore = hasMore
    )
}
