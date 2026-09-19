package com.pitahmzalendo.kejafy.feature.listings.domain.model

data class AgentSummary(
    val id: Long,
    val name: String,
    val phoneNumber: String?,
    val profileImageUrl: String? = null
)
