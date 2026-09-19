package com.pitahmzalendo.kejafy.feature.messaging.data.repository

import com.pitahmzalendo.kejafy.feature.messaging.data.remote.ChatApi
import com.pitahmzalendo.kejafy.feature.messaging.data.remote.dto.SendMessageRequestDto
import com.pitahmzalendo.kejafy.feature.messaging.domain.model.Conversation
import com.pitahmzalendo.kejafy.feature.messaging.domain.model.Message
import com.pitahmzalendo.kejafy.feature.messaging.domain.repository.ChatRepository

class ChatRepositoryImpl(private val api: ChatApi) : ChatRepository {

    override suspend fun getConversations(): Result<List<Conversation>> = runCatching {
        api.getConversations().data.map { dto ->
            Conversation(
                id = dto.id,
                otherParticipantName = dto.otherParticipantName,
                otherParticipantAvatarUrl = dto.otherParticipantAvatarUrl,
                lastMessage = null, // Backend doesn't return body in list
                lastMessageAt = dto.lastMessageAt,
                unreadCount = dto.unreadCount
            )
        }
    }

    override suspend fun getMessages(conversationId: Long): Result<List<Message>> = runCatching {
        api.getMessages(conversationId).data.map { dto ->
            Message(
                id = dto.id,
                senderName = dto.senderName,
                isMine = dto.mine,
                body = dto.body,
                sentAt = dto.createdAt
            )
        }
    }

    override suspend fun sendMessage(conversationId: Long, body: String): Result<Message> = runCatching {
        val dto = api.sendMessage(conversationId, SendMessageRequestDto(body))
        Message(
            id = dto.id,
            senderName = dto.senderName,
            isMine = dto.mine,
            body = dto.body,
            sentAt = dto.createdAt
        )
    }

    override suspend fun startConversation(listingId: Long): Result<Conversation> = runCatching {
        val dto = api.startConversation(listingId)
        Conversation(
            id = dto.id,
            otherParticipantName = dto.otherParticipantName,
            otherParticipantAvatarUrl = dto.otherParticipantAvatarUrl,
            lastMessage = null,
            lastMessageAt = dto.lastMessageAt,
            unreadCount = dto.unreadCount
        )
    }
}
