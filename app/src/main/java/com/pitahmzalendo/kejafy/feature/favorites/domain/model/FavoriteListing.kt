package com.pitahmzalendo.kejafy.feature.favorites.domain.model

import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing

data class FavoriteListing(
    val id: Long,
    val listing: PropertyListing,
    val savedAt: Long
)
