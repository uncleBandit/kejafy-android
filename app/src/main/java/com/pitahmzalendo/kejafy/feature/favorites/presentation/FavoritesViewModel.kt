package com.pitahmzalendo.kejafy.feature.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pitahmzalendo.kejafy.feature.favorites.domain.usecase.GetFavoritesUseCase
import com.pitahmzalendo.kejafy.feature.favorites.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getFavoritesUseCase().collect { favorites ->
                _uiState.update { it.copy(favorites = favorites, isLoading = false) }
            }
        }
    }

    fun onToggleFavorite(listingId: Long) {
        viewModelScope.launch {
            toggleFavoriteUseCase(listingId)
        }
    }
}
