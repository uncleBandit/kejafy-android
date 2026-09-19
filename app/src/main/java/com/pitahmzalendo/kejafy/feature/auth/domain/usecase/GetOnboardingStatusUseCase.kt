package com.pitahmzalendo.kejafy.feature.auth.domain.usecase

import com.pitahmzalendo.kejafy.feature.auth.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow

class GetOnboardingStatusUseCase(private val repository: OnboardingRepository) {
    operator fun invoke(): Flow<Boolean> = repository.isOnboardingCompleted()
}
