package com.pitahmzalendo.kejafy.feature.listings.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pitahmzalendo.kejafy.feature.listings.domain.usecase.GetListingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PropertyDetailsViewModel(
    private val getListingUseCase: GetListingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PropertyDetailsUiState>(PropertyDetailsUiState.Loading)
    val uiState: StateFlow<PropertyDetailsUiState> = _uiState.asStateFlow()

    fun loadListing(id: Long) {
        viewModelScope.launch {
            _uiState.value = PropertyDetailsUiState.Loading
            getListingUseCase(id)
                .onSuccess { listing ->
                    _uiState.value = PropertyDetailsUiState.Success(listing)
                }
                .onFailure { error ->
                    _uiState.value = PropertyDetailsUiState.Error(error.message ?: "Unknown error")
                }
        }
    }
}
