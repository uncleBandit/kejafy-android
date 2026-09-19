package com.pitahmzalendo.kejafy.feature.reels.presentation

import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyReel

data class ReelFeedUiState(
    val reels: List<PropertyReel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentReelIndex: Int = 0
)
