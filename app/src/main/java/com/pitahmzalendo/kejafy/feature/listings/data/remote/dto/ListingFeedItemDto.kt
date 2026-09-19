package com.pitahmzalendo.kejafy.feature.listings.data.remote.dto

import java.math.BigDecimal

data class ListingFeedItemDto(
    val id: Long,
    val locked: Boolean,
    val videos: List<ListingVideoDto>,
    val likeCount: Long,
    val likedByMe: Boolean,
    val commentCount: Long,
    val amenities: List<ListingAmenityDto>,
    val environmentProfile: EnvironmentProfileDto?,
    val title: String?,
    val description: String?,
    val propertyType: String?,
    val purpose: String?,
    val priceAmount: BigDecimal?,
    val priceCurrency: String?,
    val bedrooms: Int?,
    val bathrooms: Int?,
    val neighbourhood: String?,
    val latitude: Double?,
    val longitude: Double?,
    val distanceKm: Double?
)
