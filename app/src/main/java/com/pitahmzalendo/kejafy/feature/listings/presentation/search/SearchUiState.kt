package com.pitahmzalendo.kejafy.feature.listings.presentation.search

import com.pitahmzalendo.kejafy.feature.listings.domain.model.ListingFilters
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing

data class SearchUiState(
    val query: String = "",
    val filters: ListingFilters = ListingFilters(),
    val results: List<PropertyListing> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
