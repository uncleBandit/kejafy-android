package com.pitahmzalendo.kejafy.feature.listings.data.remote.dto

import java.math.BigDecimal

data class ListingDetailDto(
    val id: Long,
    val agentId: Long,
    val agentName: String,
    val agentPhone: String?,
    val title: String,
    val description: String?,
    val propertyType: String,
    val purpose: String,
    val priceAmount: BigDecimal,
    val priceCurrency: String,
    val bedrooms: Int?,
    val bathrooms: Int?,
    val neighbourhood: String?,
    val latitude: Double?,
    val longitude: Double?,
    val status: String,
    val verified: Boolean,
    val viewCount: Long,
    val likeCount: Long,
    val commentCount: Long,
    val videos: List<ListingVideoDto>,
    val amenities: List<ListingAmenityDto>,
    val environmentProfile: EnvironmentProfileDto?
)
