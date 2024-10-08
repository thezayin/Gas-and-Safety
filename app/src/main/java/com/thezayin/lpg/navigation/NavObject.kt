package com.thezayin.lpg.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashScreenNav

@Serializable
object HomeScreenNav

@Serializable
object AddressScreenNav

@Serializable
object ProfileScreenNav

@Serializable
object CartScreenNav

@Serializable
object OrderScreenNav

@Serializable
object ContactUsScreenNav

@Serializable
object SettingScreenNav

@Serializable
object HistoryScreenNav

@Serializable
data class ProductScreenNav(val productId: String)