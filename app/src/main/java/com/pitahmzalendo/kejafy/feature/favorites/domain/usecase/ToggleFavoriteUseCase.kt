package com.pitahmzalendo.kejafy.feature.favorites.domain.usecase

import com.pitahmzalendo.kejafy.feature.favorites.domain.repository.FavoriteRepository

class ToggleFavoriteUseCase(private val repository: FavoriteRepository) {
    suspend operator fun invoke(listingId: Long): Result<Unit> = repository.toggleFavorite(listingId)
}
