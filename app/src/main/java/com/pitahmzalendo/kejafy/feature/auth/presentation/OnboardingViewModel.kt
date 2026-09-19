package com.pitahmzalendo.kejafy.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pitahmzalendo.kejafy.feature.auth.domain.usecase.CompleteOnboardingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val completeOnboardingUseCase: CompleteOnboardingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    fun selectIntent(intent: UserIntent) {
        _uiState.update { it.copy(selectedIntent = intent, currentStep = OnboardingStep.PREFERRED_AREA) }
    }

    fun updatePreferredArea(area: String) {
        _uiState.update { it.copy(preferredArea = area) }
    }

    fun updatePropertyType(type: String) {
        _uiState.update { it.copy(propertyType = type) }
    }

    fun updatePriceRange(min: String, max: String) {
        _uiState.update { it.copy(minPrice = min, maxPrice = max) }
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        _uiState.update { it.copy(notificationsEnabled = enabled) }
    }

    fun onNextClick() {
        val nextStep = when (_uiState.value.currentStep) {
            OnboardingStep.WELCOME -> OnboardingStep.INTENT_SELECTION
            OnboardingStep.INTENT_SELECTION -> OnboardingStep.PREFERRED_AREA
            OnboardingStep.PREFERRED_AREA -> OnboardingStep.PROPERTY_TYPE
            OnboardingStep.PROPERTY_TYPE -> OnboardingStep.PRICE_RANGE
            OnboardingStep.PRICE_RANGE -> OnboardingStep.NOTIFICATIONS
            OnboardingStep.NOTIFICATIONS -> OnboardingStep.ACCOUNT_CREATION
            OnboardingStep.ACCOUNT_CREATION -> {
                completeOnboarding()
                return
            }
        }
        _uiState.update { it.copy(currentStep = nextStep) }
    }

    fun onBackClick() {
        val prevStep = when (_uiState.value.currentStep) {
            OnboardingStep.WELCOME -> OnboardingStep.WELCOME
            OnboardingStep.INTENT_SELECTION -> OnboardingStep.WELCOME
            OnboardingStep.PREFERRED_AREA -> OnboardingStep.INTENT_SELECTION
            OnboardingStep.PROPERTY_TYPE -> OnboardingStep.PREFERRED_AREA
            OnboardingStep.PRICE_RANGE -> OnboardingStep.PROPERTY_TYPE
            OnboardingStep.NOTIFICATIONS -> OnboardingStep.PRICE_RANGE
            OnboardingStep.ACCOUNT_CREATION -> OnboardingStep.NOTIFICATIONS
        }
        _uiState.update { it.copy(currentStep = prevStep) }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            completeOnboardingUseCase()
            _uiState.update { it.copy(isCompleted = true) }
        }
    }
}
