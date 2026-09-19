package com.pitahmzalendo.kejafy.feature.reels.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pitahmzalendo.kejafy.feature.listings.domain.usecase.GetReelFeedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReelFeedViewModel(
    private val getReelFeedUseCase: GetReelFeedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReelFeedUiState())
    val uiState: StateFlow<ReelFeedUiState> = _uiState.asStateFlow()

    init {
        loadFeed()
    }

    fun loadFeed() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = getReelFeedUseCase()
            result.onSuccess { pagedResult ->
                _uiState.update { it.copy(
                    reels = pagedResult.data,
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
}
