package com.pitahmzalendo.kejafy.feature.listings.data.remote.dto

data class FactorScoreDto(
    val score: Int,
    val level: String,
    val explanation: String?
)

data class EnvironmentProfileDto(
    val overallScore: Int?,
    val noiseLevel: Int?,
    val floodRiskLevel: Int?,
    val roadAccessQuality: Int?,
    val securityLevel: Int?,
    val waterReliability: Int?,
    val powerReliability: Int?
)
