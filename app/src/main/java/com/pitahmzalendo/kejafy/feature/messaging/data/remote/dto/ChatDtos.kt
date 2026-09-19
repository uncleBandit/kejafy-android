package com.pitahmzalendo.kejafy.feature.messaging.data.remote.dto

data class ConversationResponseDto(
    val id: Long,
    val otherParticipantName: String,
    val otherParticipantAvatarUrl: String?,
    val unreadCount: Long,
    val lastMessageAt: String?
)

data class MessageResponseDto(
    val id: Long,
    val senderName: String,
    val mine: Boolean,
    val body: String,
    val createdAt: String
)

data class SendMessageRequestDto(
    val body: String
)
