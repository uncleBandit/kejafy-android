package com.pitahmzalendo.kejafy.feature.listings.presentation.details

import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing

sealed class PropertyDetailsUiState {
    object Loading : PropertyDetailsUiState()
    data class Success(val listing: PropertyListing) : PropertyDetailsUiState()
    data class Error(val message: String) : PropertyDetailsUiState()
}
