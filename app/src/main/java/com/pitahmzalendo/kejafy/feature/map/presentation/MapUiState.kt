package com.pitahmzalendo.kejafy.feature.map.presentation

import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing

data class MapUiState(
    val listings: List<PropertyListing> = emptyList(),
    val isLoading: Boolean = false,
    val userLocation: Pair<Double, Double>? = null,
    val error: String? = null
)
