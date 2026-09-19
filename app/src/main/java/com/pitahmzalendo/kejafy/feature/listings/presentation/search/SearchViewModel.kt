package com.pitahmzalendo.kejafy.feature.listings.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pitahmzalendo.kejafy.feature.listings.domain.model.ListingFilters
import com.pitahmzalendo.kejafy.feature.listings.domain.usecase.SearchListingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchListingsUseCase: SearchListingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
        performSearch()
    }

    fun onFiltersChange(filters: ListingFilters) {
        _uiState.update { it.copy(filters = filters) }
        performSearch()
    }

    private fun performSearch() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            searchListingsUseCase(_uiState.value.filters).onSuccess { result ->
                _uiState.update { it.copy(results = result.data, isLoading = false) }
            }.onFailure { error ->
                _uiState.update { it.copy(error = error.message, isLoading = false) }
            }
        }
    }
}
