package com.pitahmzalendo.kejafy.feature.listings.domain.model

enum class RiskLevel {
    LOW,
    MEDIUM,
    HIGH,
    UNKNOWN
}

data class FactorScore(
    val score: Int,
    val level: RiskLevel,
    val explanation: String? = null
)

data class EnvironmentScore(
    val overall: Int?,
    val noise: FactorScore? = null,
    val floodRisk: FactorScore? = null,
    val security: FactorScore? = null,
    val utilities: FactorScore? = null
)
