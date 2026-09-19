package com.pitahmzalendo.kejafy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pitahmzalendo.kejafy.feature.auth.presentation.OnboardingScreen
import com.pitahmzalendo.kejafy.feature.auth.presentation.OnboardingViewModel
import com.pitahmzalendo.kejafy.feature.reels.presentation.ReelFeedScreen
import com.pitahmzalendo.kejafy.feature.reels.presentation.ReelFeedViewModel
import com.pitahmzalendo.kejafy.feature.listings.presentation.details.PropertyDetailsScreen
import com.pitahmzalendo.kejafy.feature.listings.presentation.details.PropertyDetailsViewModel
import com.pitahmzalendo.kejafy.feature.map.presentation.MapScreen
import com.pitahmzalendo.kejafy.feature.map.presentation.MapViewModel
import com.pitahmzalendo.kejafy.feature.listings.presentation.search.SearchScreen
import com.pitahmzalendo.kejafy.feature.listings.presentation.search.SearchViewModel
import com.pitahmzalendo.kejafy.feature.favorites.presentation.FavoritesScreen
import com.pitahmzalendo.kejafy.feature.favorites.presentation.FavoritesViewModel
import com.pitahmzalendo.kejafy.feature.messaging.presentation.ConversationListScreen
import com.pitahmzalendo.kejafy.feature.messaging.presentation.ChatScreen
import com.pitahmzalendo.kejafy.feature.messaging.presentation.ChatViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.DisposableEffect
import androidx.compose.material3.Text

@Composable
fun KejafyNavHost(
    navController: NavHostController,
    onboardingViewModel: OnboardingViewModel,
    reelFeedViewModel: ReelFeedViewModel,
    propertyDetailsViewModel: PropertyDetailsViewModel,
    mapViewModel: MapViewModel,
    searchViewModel: SearchViewModel,
    favoritesViewModel: FavoritesViewModel,
    chatViewModel: ChatViewModel,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Onboarding.route) {
            val state by onboardingViewModel.uiState.collectAsState()
            
            if (state.isCompleted) {
                navController.navigate(Screen.ReelFeed.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            }
            
            OnboardingScreen(
                state = state,
                onNextClick = onboardingViewModel::onNextClick,
                onBackClick = onboardingViewModel::onBackClick,
                onIntentSelect = onboardingViewModel::selectIntent,
                onAreaChange = onboardingViewModel::updatePreferredArea,
                onTypeChange = onboardingViewModel::updatePropertyType,
                onPriceRangeChange = onboardingViewModel::updatePriceRange,
                onNotificationsToggle = onboardingViewModel::setNotificationsEnabled,
                onGuestContinueClick = {
                    onboardingViewModel.completeOnboarding()
                }
            )
        }
        
        composable(Screen.Auth.route) {
            Text("Authentication Screen Placeholder")
        }

        composable(Screen.ReelFeed.route) {
            val state by reelFeedViewModel.uiState.collectAsState()
            ReelFeedScreen(
                state = state,
                onLikeClick = { /* TODO */ },
                onShareClick = { /* TODO */ },
                onViewPropertyClick = { propertyId ->
                    navController.navigate(Screen.PropertyDetails.createRoute(propertyId))
                }
            )
        }

        composable(Screen.Map.route) {
            val state by mapViewModel.uiState.collectAsState()
            MapScreen(
                state = state,
                onMarkerClick = { propertyId ->
                    navController.navigate(Screen.PropertyDetails.createRoute(propertyId))
                }
            )
        }

        composable(Screen.Search.route) {
            val state by searchViewModel.uiState.collectAsState()
            SearchScreen(
                state = state,
                onQueryChange = searchViewModel::onQueryChange,
                onFilterClick = { /* TODO: Open Filter Dialog */ },
                onListingClick = { propertyId ->
                    navController.navigate(Screen.PropertyDetails.createRoute(propertyId))
                }
            )
        }

        composable(Screen.Favorites.route) {
            val state by favoritesViewModel.uiState.collectAsState()
            FavoritesScreen(
                state = state,
                onListingClick = { propertyId ->
                    navController.navigate(Screen.PropertyDetails.createRoute(propertyId))
                }
            )
        }

        composable(Screen.Conversations.route) {
            val state by chatViewModel.listState.collectAsState()
            ConversationListScreen(
                state = state,
                onConversationClick = { conversationId ->
                    navController.navigate(Screen.Chat.createRoute(conversationId))
                }
            )
            DisposableEffect(Unit) {
                chatViewModel.loadConversations()
                onDispose {}
            }
        }

        composable(
            route = Screen.Chat.route,
            arguments = listOf(navArgument("conversationId") { type = NavType.LongType })
        ) { backStackEntry ->
            val conversationId = backStackEntry.arguments?.getLong("conversationId") ?: 0L
            val state by chatViewModel.chatState.collectAsState()
            
            ChatScreen(
                state = state,
                onSendMessage = { body -> chatViewModel.sendMessage(conversationId, body) },
                onBackClick = { navController.popBackStack() }
            )
            
            DisposableEffect(conversationId) {
                chatViewModel.loadMessages(conversationId)
                onDispose {}
            }
        }

        composable(
            route = Screen.PropertyDetails.route,
            arguments = listOf(navArgument("propertyId") { type = NavType.LongType })
        ) { backStackEntry ->
            val propertyId = backStackEntry.arguments?.getLong("propertyId") ?: 0L
            val state by propertyDetailsViewModel.uiState.collectAsState()
            
            PropertyDetailsScreen(
                state = state,
                onBackClick = { navController.popBackStack() }
            )
            
            // Trigger load if not already loaded or different ID
            DisposableEffect(propertyId) {
                propertyDetailsViewModel.loadListing(propertyId)
                onDispose {}
            }
        }
        
        composable(Screen.Home.route) {
            Text("Home Screen Placeholder")
        }
    }
}
