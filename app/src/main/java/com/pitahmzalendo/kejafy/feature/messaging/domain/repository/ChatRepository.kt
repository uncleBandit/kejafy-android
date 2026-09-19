package com.pitahmzalendo.kejafy.feature.messaging.domain.repository

import com.pitahmzalendo.kejafy.feature.messaging.domain.model.Conversation
import com.pitahmzalendo.kejafy.feature.messaging.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getConversations(): Result<List<Conversation>>
    suspend fun getMessages(conversationId: Long): Result<List<Message>>
    suspend fun sendMessage(conversationId: Long, body: String): Result<Message>
    suspend fun startConversation(listingId: Long): Result<Conversation>
}
