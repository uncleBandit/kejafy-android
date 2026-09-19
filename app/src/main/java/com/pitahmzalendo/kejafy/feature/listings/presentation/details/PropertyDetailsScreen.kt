package com.pitahmzalendo.kejafy.feature.listings.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyListing
import com.pitahmzalendo.kejafy.feature.listings.presentation.details.components.EnvironmentSection
import androidx.compose.ui.tooling.preview.Preview
import com.pitahmzalendo.kejafy.feature.listings.domain.model.ListingType
import com.pitahmzalendo.kejafy.feature.listings.domain.model.Money
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyLocation
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyType
import com.pitahmzalendo.kejafy.feature.listings.presentation.details.components.AmenitiesSection
import com.pitahmzalendo.kejafy.ui.theme.KejafyTheme
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyDetailsScreen(
    state: PropertyDetailsUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Property Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        // Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding).fillMaxSize()) {
            when (state) {
                is PropertyDetailsUiState.Loading -> CircularProgressIndicator()
                is PropertyDetailsUiState.Error -> Text(text = state.message)
                is PropertyDetailsUiState.Success -> {
                    PropertyDetailsContent(listing = state.listing)
                }
            }
        }
    }
}

@Composable
fun PropertyDetailsContent(listing: PropertyListing) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = listing.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${listing.price.currency} ${listing.price.amount}",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = listing.location.neighbourhood ?: "Unknown Area")
        
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        
        Text(text = "About this property", style = MaterialTheme.typography.titleMedium)
        Text(text = listing.description ?: "No description provided")
        
        listing.environmentScore?.let {
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            EnvironmentSection(score = it)
        }

        if (listing.amenities.isNotEmpty()) {
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            AmenitiesSection(amenities = listing.amenities)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PropertyDetailsScreenPreview() {
    KejafyTheme {
        PropertyDetailsScreen(
            state = PropertyDetailsUiState.Success(
                listing = PropertyListing(
                    id = 1L,
                    title = "Luxury 3 Bedroom Apartment",
                    description = "A beautiful luxury apartment located in a serene environment with ample parking space and 24/7 security.",
                    listingType = ListingType.RENT,
                    propertyType = PropertyType.APARTMENT,
                    price = Money(BigDecimal("120000"), "KES"),
                    location = PropertyLocation(
                        latitude = -1.2921,
                        longitude = 36.8219,
                        neighbourhood = "Kilimani",
                        city = "Nairobi",
                        country = "Kenya"
                    ),
                    bedrooms = 3,
                    bathrooms = 2,
                    amenities = emptyList(),
                    media = emptyList(),
                    environmentScore = null,
                    agent = null
                )
            ),
            onBackClick = {}
        )
    }
}
