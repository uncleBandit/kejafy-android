package com.pitahmzalendo.kejafy.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Auth : Screen("auth")
    object Home : Screen("home")
    object ReelFeed : Screen("reel_feed")
    object Map : Screen("map")
    object Search : Screen("search")
    object Favorites : Screen("favorites")
    object Conversations : Screen("conversations")
    object Chat : Screen("chat/{conversationId}") {
        fun createRoute(conversationId: Long) = "chat/$conversationId"
    }
    object PropertyDetails : Screen("property_details/{propertyId}") {
        fun createRoute(propertyId: Long) = "property_details/$propertyId"
    }
}
