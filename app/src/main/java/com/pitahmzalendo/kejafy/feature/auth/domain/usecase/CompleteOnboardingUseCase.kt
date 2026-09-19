package com.pitahmzalendo.kejafy.feature.auth.domain.usecase

import com.pitahmzalendo.kejafy.feature.auth.domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(private val repository: OnboardingRepository) {
    suspend operator fun invoke() = repository.completeOnboarding()
}
