package com.pitahmzalendo.kejafy.feature.auth.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    fun isOnboardingCompleted(): Flow<Boolean>
    suspend fun completeOnboarding()
}
