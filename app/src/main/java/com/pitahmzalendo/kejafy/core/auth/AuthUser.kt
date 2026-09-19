package com.pitahmzalendo.kejafy.core.auth

enum class UserRole {
    CUSTOMER,
    AGENT,
    ADMIN
}

data class AuthUser(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: UserRole
)
