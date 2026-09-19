package com.pitahmzalendo.kejafy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.pitahmzalendo.kejafy.feature.auth.data.OnboardingPreferences
import com.pitahmzalendo.kejafy.feature.auth.data.OnboardingPreferencesRepositoryImpl
import com.pitahmzalendo.kejafy.feature.auth.domain.usecase.CompleteOnboardingUseCase
import com.pitahmzalendo.kejafy.feature.auth.domain.usecase.GetOnboardingStatusUseCase
import com.pitahmzalendo.kejafy.feature.auth.presentation.OnboardingViewModel
import com.pitahmzalendo.kejafy.feature.reels.presentation.ReelFeedViewModel
import com.pitahmzalendo.kejafy.feature.listings.presentation.details.PropertyDetailsViewModel
import com.pitahmzalendo.kejafy.core.network.KejafyNetworkClient
import com.pitahmzalendo.kejafy.feature.listings.data.remote.ListingApi
import com.pitahmzalendo.kejafy.feature.listings.data.remote.ReelApi
import com.pitahmzalendo.kejafy.feature.listings.data.repository.ListingRepositoryImpl
import com.pitahmzalendo.kejafy.feature.listings.data.repository.ReelRepositoryImpl
import com.pitahmzalendo.kejafy.feature.listings.domain.usecase.GetListingUseCase
import com.pitahmzalendo.kejafy.feature.listings.domain.usecase.GetListingsUseCase
import com.pitahmzalendo.kejafy.feature.listings.domain.usecase.GetReelFeedUseCase
import com.pitahmzalendo.kejafy.feature.listings.domain.usecase.SearchListingsUseCase
import com.pitahmzalendo.kejafy.feature.map.presentation.MapViewModel
import com.pitahmzalendo.kejafy.feature.listings.presentation.search.SearchViewModel
import com.pitahmzalendo.kejafy.feature.favorites.data.repository.FavoriteRepositoryImpl
import com.pitahmzalendo.kejafy.feature.favorites.domain.usecase.GetFavoritesUseCase
import com.pitahmzalendo.kejafy.feature.favorites.domain.usecase.ToggleFavoriteUseCase
import com.pitahmzalendo.kejafy.feature.favorites.presentation.FavoritesViewModel
import com.pitahmzalendo.kejafy.feature.messaging.data.remote.ChatApi
import com.pitahmzalendo.kejafy.feature.messaging.data.repository.ChatRepositoryImpl
import com.pitahmzalendo.kejafy.feature.messaging.domain.usecase.GetConversationsUseCase
import com.pitahmzalendo.kejafy.feature.messaging.domain.usecase.GetMessagesUseCase
import com.pitahmzalendo.kejafy.feature.messaging.domain.usecase.SendMessageUseCase
import com.pitahmzalendo.kejafy.feature.messaging.presentation.ChatViewModel
import com.pitahmzalendo.kejafy.core.database.AppDatabase
import androidx.room.Room
import com.pitahmzalendo.kejafy.navigation.KejafyNavHost
import com.pitahmzalendo.kejafy.navigation.Screen
import com.pitahmzalendo.kejafy.ui.theme.KejafyTheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Manual DI for now
        val onboardingPrefs = OnboardingPreferences(applicationContext)
        val onboardingRepo = OnboardingPreferencesRepositoryImpl(onboardingPrefs)
        val getOnboardingStatusUseCase = GetOnboardingStatusUseCase(onboardingRepo)
        val completeOnboardingUseCase = CompleteOnboardingUseCase(onboardingRepo)

        val listingApi = KejafyNetworkClient.createService<ListingApi>()
        val reelApi = KejafyNetworkClient.createService<ReelApi>()
        
        val listingRepo = ListingRepositoryImpl(listingApi)
        val reelRepo = ReelRepositoryImpl(reelApi)
        
        val getListingUseCase = GetListingUseCase(listingRepo)
        val getListingsUseCase = GetListingsUseCase(listingRepo)
        val getReelFeedUseCase = GetReelFeedUseCase(reelRepo)
        val searchListingsUseCase = SearchListingsUseCase(listingRepo)

        val database: AppDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "kejafy_db").build()
        val favoriteRepo = FavoriteRepositoryImpl(database.favoriteDao(), listingApi)
        val getFavoritesUseCase = GetFavoritesUseCase(favoriteRepo)
        val toggleFavoriteUseCase = ToggleFavoriteUseCase(favoriteRepo)

        val chatApi = KejafyNetworkClient.createService<ChatApi>()
        val chatRepo = ChatRepositoryImpl(chatApi)
        val getConversationsUseCase = GetConversationsUseCase(chatRepo)
        val getMessagesUseCase = GetMessagesUseCase(chatRepo)
        val sendMessageUseCase = SendMessageUseCase(chatRepo)

        enableEdgeToEdge()
        setContent {
            KejafyTheme {
                val navController = rememberNavController()
                val isOnboardingCompleted by getOnboardingStatusUseCase().collectAsState(initial = null)

                if (isOnboardingCompleted != null) {
                    val startDestination = if (isOnboardingCompleted == true) Screen.ReelFeed.route else Screen.Onboarding.route
                    
                    val onboardingViewModel: OnboardingViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return OnboardingViewModel(completeOnboardingUseCase) as T
                            }
                        }
                    )
                    
                    val reelFeedViewModel: ReelFeedViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return ReelFeedViewModel(getReelFeedUseCase) as T
                            }
                        }
                    )
                    
                    val propertyDetailsViewModel: PropertyDetailsViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return PropertyDetailsViewModel(getListingUseCase) as T
                            }
                        }
                    )

                    val mapViewModel: MapViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return MapViewModel(getListingsUseCase) as T
                            }
                        }
                    )

                    val searchViewModel: SearchViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return SearchViewModel(searchListingsUseCase) as T
                            }
                        }
                    )

                    val favoritesViewModel: FavoritesViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return FavoritesViewModel(getFavoritesUseCase, toggleFavoriteUseCase) as T
                            }
                        }
                    )

                    val chatViewModel: ChatViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return ChatViewModel(getConversationsUseCase, getMessagesUseCase, sendMessageUseCase) as T
                            }
                        }
                    )

                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        KejafyNavHost(
                            navController = navController,
                            onboardingViewModel = onboardingViewModel,
                            reelFeedViewModel = reelFeedViewModel,
                            propertyDetailsViewModel = propertyDetailsViewModel,
                            mapViewModel = mapViewModel,
                            searchViewModel = searchViewModel,
                            favoritesViewModel = favoritesViewModel,
                            chatViewModel = chatViewModel,
                            startDestination = startDestination,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
