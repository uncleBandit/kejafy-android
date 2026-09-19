package com.pitahmzalendo.kejafy.feature.messaging.domain.model

data class Message(
    val id: Long,
    val senderName: String,
    val isMine: Boolean,
    val body: String,
    val sentAt: String
)
