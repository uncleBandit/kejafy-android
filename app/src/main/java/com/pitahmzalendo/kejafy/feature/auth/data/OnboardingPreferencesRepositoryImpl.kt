package com.pitahmzalendo.kejafy.feature.auth.data

import com.pitahmzalendo.kejafy.feature.auth.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow

class OnboardingPreferencesRepositoryImpl(
    private val onboardingPreferences: OnboardingPreferences
) : OnboardingRepository {
    
    override fun isOnboardingCompleted(): Flow<Boolean> {
        return onboardingPreferences.isOnboardingCompleted
    }

    override suspend fun completeOnboarding() {
        onboardingPreferences.saveOnboardingStatus(true)
    }
}
