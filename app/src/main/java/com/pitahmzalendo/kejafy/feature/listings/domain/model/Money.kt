package com.pitahmzalendo.kejafy.feature.listings.domain.model

import java.math.BigDecimal

data class Money(
    val amount: BigDecimal,
    val currency: String
)
