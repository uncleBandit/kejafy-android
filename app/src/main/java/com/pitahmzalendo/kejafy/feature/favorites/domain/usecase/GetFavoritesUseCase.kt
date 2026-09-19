package com.pitahmzalendo.kejafy.feature.favorites.domain.usecase

import com.pitahmzalendo.kejafy.feature.favorites.domain.repository.FavoriteRepository
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(): Flow<List<PropertyListing>> = repository.getFavorites()
}
