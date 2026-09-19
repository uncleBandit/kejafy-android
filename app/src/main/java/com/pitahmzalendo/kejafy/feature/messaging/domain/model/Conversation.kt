package com.pitahmzalendo.kejafy.feature.messaging.domain.model

data class Conversation(
    val id: Long,
    val otherParticipantName: String,
    val otherParticipantAvatarUrl: String?,
    val lastMessage: String?,
    val lastMessageAt: String?,
    val unreadCount: Long
)
