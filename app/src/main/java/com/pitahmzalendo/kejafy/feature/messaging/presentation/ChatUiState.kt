package com.pitahmzalendo.kejafy.feature.messaging.presentation

import com.pitahmzalendo.kejafy.feature.messaging.domain.model.Conversation
import com.pitahmzalendo.kejafy.feature.messaging.domain.model.Message

data class ConversationListUiState(
    val conversations: List<Conversation> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class ChatUiState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
