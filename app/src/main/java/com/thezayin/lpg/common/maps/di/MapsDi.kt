package com.thezayin.lpg.common.maps.di

import com.google.android.gms.location.LocationServices
import com.thezayin.lpg.common.maps.data.LocationServiceImpl
import com.thezayin.lpg.common.maps.domain.repository.LocationService
import com.thezayin.lpg.common.maps.domain.usecase.GetLocation
import com.thezayin.lpg.common.maps.domain.usecase.GetLocationImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mapsModule = module {
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    singleOf(::LocationServiceImpl) bind LocationService::class
    singleOf(::GetLocationImpl) bind GetLocation::class
}