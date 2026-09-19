package com.pitahmzalendo.kejafy.feature.listings.presentation.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pitahmzalendo.kejafy.feature.listings.domain.model.Amenity
import com.pitahmzalendo.kejafy.feature.listings.domain.model.AmenityCategory

@Composable
fun AmenitiesSection(
    amenities: List<Amenity>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Nearby Amenities",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        amenities.forEach { amenity ->
            AmenityItem(amenity = amenity)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun AmenityItem(amenity: Amenity) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = amenity.category.toIcon(),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = amenity.name, style = MaterialTheme.typography.bodyLarge)
            amenity.distanceMeters?.let {
                val distanceText = if (it >= 1000) "${"%.1f".format(it / 1000f)} km" else "$it m"
                Text(
                    text = distanceText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private fun AmenityCategory.toIcon(): ImageVector = when (this) {
    AmenityCategory.EDUCATION -> Icons.Default.School
    AmenityCategory.HEALTHCARE -> Icons.Default.LocalHospital
    AmenityCategory.TRANSPORT -> Icons.Default.DirectionsBus
    AmenityCategory.SHOPPING -> Icons.Default.LocalMall
    AmenityCategory.SECURITY -> Icons.Default.Security
    AmenityCategory.RECREATION -> Icons.Default.Park
    AmenityCategory.FINANCIAL -> Icons.Default.AccountBalance
    AmenityCategory.DINING -> Icons.Default.Restaurant
    AmenityCategory.WORSHIP -> Icons.Default.Church
    AmenityCategory.FUEL -> Icons.Default.LocalGasStation
    AmenityCategory.OTHER -> Icons.Default.Place
}
