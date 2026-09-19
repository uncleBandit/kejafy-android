package com.pitahmzalendo.kejafy.feature.auth.domain.model

data class UserOnboardingState(
    val isCompleted: Boolean = false,
    val username: String? = null
)
