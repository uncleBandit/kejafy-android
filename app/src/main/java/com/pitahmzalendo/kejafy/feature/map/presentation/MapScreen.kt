package com.pitahmzalendo.kejafy.feature.map.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen(
    state: MapUiState,
    onMarkerClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val nairobi = LatLng(-1.2921, 36.8219)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(nairobi, 12f)
    }

    Scaffold { innerPadding ->
        Box(modifier = modifier.padding(innerPadding).fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = false) // Permission logic needed
            ) {
                state.listings.forEach { listing ->
                    val markerState = rememberMarkerState(position = LatLng(listing.location.latitude, listing.location.longitude))
                    Marker(
                        state = markerState,
                        title = listing.title,
                        snippet = "${listing.price.currency} ${listing.price.amount}",
                        onClick = {
                            onMarkerClick(listing.id)
                            false
                        }
                    )
                }
            }
        }
    }
}
