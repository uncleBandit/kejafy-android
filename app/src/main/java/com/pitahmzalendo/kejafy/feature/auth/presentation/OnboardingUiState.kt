package com.pitahmzalendo.kejafy.feature.auth.presentation

enum class UserIntent { RENT, BUY, INVEST }
enum class OnboardingStep {
    WELCOME,
    INTENT_SELECTION,
    PREFERRED_AREA,
    PROPERTY_TYPE,
    PRICE_RANGE,
    NOTIFICATIONS,
    ACCOUNT_CREATION
}

data class OnboardingUiState(
    val currentStep: OnboardingStep = OnboardingStep.WELCOME,
    val selectedIntent: UserIntent? = null,
    val preferredArea: String = "",
    val propertyType: String = "",
    val minPrice: String = "",
    val maxPrice: String = "",
    val notificationsEnabled: Boolean = false,
    val isCompleted: Boolean = false
)
