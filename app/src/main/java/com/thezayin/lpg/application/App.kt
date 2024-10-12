package com.thezayin.lpg.application

import android.app.Application
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.thezayin.analytics.di.analyticsModule
import com.thezayin.databases.di.databaseModule
import com.thezayin.framework.di.frameworkModule
import com.thezayin.presentation.di.addressModule
import com.thezayin.presentation.di.cartModule
import com.thezayin.presentation.di.historyModule
import com.thezayin.presentation.di.homeModule
import com.thezayin.presentation.di.orderModule
import com.thezayin.splash.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseAppCheck.getInstance().installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(analyticsModule)
            modules(splashModule)
            modules(homeModule)
            modules(frameworkModule)
            modules(databaseModule)
            modules(cartModule)
            modules(addressModule)
            modules(orderModule)
            modules(historyModule)
        }
    }
}