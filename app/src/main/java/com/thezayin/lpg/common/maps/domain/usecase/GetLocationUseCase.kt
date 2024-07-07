package com.thezayin.lpg.common.maps.domain.usecase

import com.google.android.gms.maps.model.LatLng
import com.thezayin.lpg.common.maps.domain.repository.LocationService
import kotlinx.coroutines.flow.Flow

interface GetLocation : suspend () -> Flow<LatLng?>

class GetLocationImpl(
    private val locationService: LocationService
) : GetLocation {
    override suspend operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()

}