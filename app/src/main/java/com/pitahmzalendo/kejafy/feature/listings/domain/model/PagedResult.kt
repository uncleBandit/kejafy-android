package com.pitahmzalendo.kejafy.feature.listings.domain.model

data class PagedResult<T>(
    val data: List<T>,
    val nextCursor: String?,
    val hasMore: Boolean
)
