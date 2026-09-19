package com.pitahmzalendo.kejafy.core.location

data class DeviceLocation(val latitude: Double, val longitude: Double)

interface LocationTracker {
    suspend fun getCurrentLocation(): DeviceLocation?
}
