package com.pitahmzalendo.kejafy.feature.messaging.data.remote

import com.pitahmzalendo.kejafy.feature.messaging.data.remote.dto.*
import com.pitahmzalendo.kejafy.feature.listings.data.remote.dto.PagedResponseDto
import retrofit2.http.*

interface ChatApi {
    @GET("api/v1/conversations")
    suspend fun getConversations(): PagedResponseDto<ConversationResponseDto>

    @GET("api/v1/conversations/{id}/messages")
    suspend fun getMessages(@Path("id") id: Long): PagedResponseDto<MessageResponseDto>

    @POST("api/v1/conversations/{id}/messages")
    suspend fun sendMessage(
        @Path("id") id: Long,
        @Body request: SendMessageRequestDto
    ): MessageResponseDto

    @POST("api/v1/listings/{listingId}/chat")
    suspend fun startConversation(@Path("listingId") listingId: Long): ConversationResponseDto
}
