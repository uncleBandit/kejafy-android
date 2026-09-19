package com.pitahmzalendo.kejafy.feature.messaging.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pitahmzalendo.kejafy.feature.messaging.domain.model.Conversation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationListScreen(
    state: ConversationListUiState,
    onConversationClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Messages") })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(state.conversations) { conversation ->
                ConversationItem(
                    conversation = conversation,
                    onClick = { onConversationClick(conversation.id) }
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun ConversationItem(conversation: Conversation, onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text(conversation.otherParticipantName) },
        supportingContent = { Text(conversation.lastMessage ?: "No messages yet") },
        trailingContent = {
            if (conversation.unreadCount > 0) {
                Badge { Text(conversation.unreadCount.toString()) }
            }
        },
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)
    )
}
