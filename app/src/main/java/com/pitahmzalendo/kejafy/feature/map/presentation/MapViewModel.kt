package com.pitahmzalendo.kejafy.feature.map.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pitahmzalendo.kejafy.feature.listings.domain.usecase.GetListingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapViewModel(
    private val getListingsUseCase: GetListingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()

    init {
        loadListings()
    }

    fun loadListings() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getListingsUseCase().onSuccess { pagedResult ->
                _uiState.update { it.copy(
                    listings = pagedResult.data,
                    isLoading = false
                ) }
            }.onFailure { error ->
                _uiState.update { it.copy(
                    error = error.message,
                    isLoading = false
                ) }
            }
        }
    }

    fun onMarkerClick(listingId: Long) {
        // Handle marker click logic if needed
    }
}
