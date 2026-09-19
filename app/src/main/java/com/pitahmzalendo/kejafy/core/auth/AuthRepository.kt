package com.pitahmzalendo.kejafy.core.auth

interface AuthRepository {
    suspend fun getAuthenticatedUser(): Result<AuthUser>
    suspend fun logout(): Result<Unit>
}
