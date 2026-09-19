package com.pitahmzalendo.kejafy.feature.favorites.presentation

import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing

data class FavoritesUiState(
    val favorites: List<PropertyListing> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
