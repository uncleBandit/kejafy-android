package com.pitahmzalendo.kejafy.feature.listings.data.remote

import com.pitahmzalendo.kejafy.feature.listings.data.remote.dto.ListingVideoDto
import com.pitahmzalendo.kejafy.feature.listings.data.remote.dto.PagedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ReelApi {

    @GET("api/v1/reels/feed")
    suspend fun getFeed(
        @Query("cursor") cursor: String? = null
    ): PagedResponseDto<ListingVideoDto>
}
