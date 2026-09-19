package com.pitahmzalendo.kejafy.feature.messaging.domain.usecase

import com.pitahmzalendo.kejafy.feature.messaging.domain.model.Conversation
import com.pitahmzalendo.kejafy.feature.messaging.domain.model.Message
import com.pitahmzalendo.kejafy.feature.messaging.domain.repository.ChatRepository

class GetConversationsUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(): Result<List<Conversation>> = repository.getConversations()
}

class GetMessagesUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(conversationId: Long): Result<List<Message>> = repository.getMessages(conversationId)
}

class SendMessageUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(conversationId: Long, body: String): Result<Message> = repository.sendMessage(conversationId, body)
}
