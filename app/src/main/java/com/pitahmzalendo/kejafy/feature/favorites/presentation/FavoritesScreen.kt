package com.pitahmzalendo.kejafy.feature.favorites.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    state: FavoritesUiState,
    onListingClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Favorites") })
        }
    ) { innerPadding ->
        if (state.favorites.isEmpty() && !state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No favorites yet.")
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.favorites) { listing ->
                    FavoriteItem(listing = listing, onClick = { onListingClick(listing.id) })
                }
            }
        }
    }
}

@Composable
private fun FavoriteItem(listing: PropertyListing, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = listing.title, style = MaterialTheme.typography.titleLarge)
            Text(
                text = "${listing.price.currency} ${listing.price.amount}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(text = listing.location.neighbourhood ?: "Unknown Area", style = MaterialTheme.typography.bodySmall)
        }
    }
}
