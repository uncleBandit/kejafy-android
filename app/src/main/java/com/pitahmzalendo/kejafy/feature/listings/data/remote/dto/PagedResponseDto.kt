package com.pitahmzalendo.kejafy.feature.listings.data.remote.dto

data class PagedResponseDto<T>(
    val data: List<T>,
    val nextCursor: String?,
    val hasMore: Boolean
)
