package com.pitahmzalendo.kejafy.feature.listings.data.remote

import com.pitahmzalendo.kejafy.feature.listings.data.remote.dto.ListingDetailDto
import com.pitahmzalendo.kejafy.feature.listings.data.remote.dto.ListingFeedItemDto
import com.pitahmzalendo.kejafy.feature.listings.data.remote.dto.PagedResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListingApi {

    @GET("api/v1/listings/{id}")
    suspend fun getListing(
        @Path("id") id: Long
    ): ListingDetailDto

    @GET("api/v1/listings")
    suspend fun getRecommendedListings(
        @Query("cursor") cursor: String? = null
    ): PagedResponseDto<ListingFeedItemDto>

    @GET("api/v1/listings/search")
    suspend fun searchListings(
        @Query("purpose") purpose: String? = null,
        @Query("propertyTypes") propertyTypes: List<String>? = null,
        @Query("minPrice") minPrice: Long? = null,
        @Query("maxPrice") maxPrice: Long? = null,
        @Query("bedrooms") bedrooms: Int? = null,
        @Query("bathrooms") bathrooms: Int? = null,
        @Query("latitude") latitude: Double? = null,
        @Query("longitude") longitude: Double? = null,
        @Query("radiusKm") radiusKm: Double? = null,
        @Query("cursor") cursor: String? = null
    ): PagedResponseDto<ListingFeedItemDto>
}
