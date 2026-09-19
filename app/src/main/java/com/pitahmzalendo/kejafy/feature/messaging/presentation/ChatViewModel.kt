package com.pitahmzalendo.kejafy.feature.messaging.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pitahmzalendo.kejafy.feature.messaging.domain.usecase.GetConversationsUseCase
import com.pitahmzalendo.kejafy.feature.messaging.domain.usecase.GetMessagesUseCase
import com.pitahmzalendo.kejafy.feature.messaging.domain.usecase.SendMessageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(
    private val getConversationsUseCase: GetConversationsUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    private val _listState = MutableStateFlow(ConversationListUiState())
    val listState: StateFlow<ConversationListUiState> = _listState.asStateFlow()

    private val _chatState = MutableStateFlow(ChatUiState())
    val chatState: StateFlow<ChatUiState> = _chatState.asStateFlow()

    fun loadConversations() {
        viewModelScope.launch {
            _listState.update { it.copy(isLoading = true) }
            getConversationsUseCase().onSuccess { list ->
                _listState.update { it.copy(conversations = list, isLoading = false) }
            }.onFailure { error ->
                _listState.update { it.copy(error = error.message, isLoading = false) }
            }
        }
    }

    fun loadMessages(conversationId: Long) {
        viewModelScope.launch {
            _chatState.update { it.copy(isLoading = true) }
            getMessagesUseCase(conversationId).onSuccess { list ->
                _chatState.update { it.copy(messages = list, isLoading = false) }
            }.onFailure { error ->
                _chatState.update { it.copy(error = error.message, isLoading = false) }
            }
        }
    }

    fun sendMessage(conversationId: Long, body: String) {
        viewModelScope.launch {
            sendMessageUseCase(conversationId, body).onSuccess { message ->
                _chatState.update { it.copy(messages = it.messages + message) }
            }
        }
    }
}
