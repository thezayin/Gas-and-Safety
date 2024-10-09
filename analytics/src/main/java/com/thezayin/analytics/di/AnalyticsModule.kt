package com.thezayin.analytics.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.thezayin.analytics.analytics.Analytics
import com.thezayin.analytics.analytics.AnalyticsImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    single { FirebaseAnalytics.getInstance(get()) }
    factoryOf(::AnalyticsImpl) bind Analytics::class
}