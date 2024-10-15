package com.thezayin.lpg.application

import android.app.Application
import com.google.firebase.FirebaseApp
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

        // Initialize Firebase App Check
        FirebaseAppCheck.getInstance().installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )

        // Initialize Firebase with the service account options
        FirebaseApp.initializeApp(this)

        // Start Koin for Dependency Injection
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                analyticsModule,
                splashModule,
                homeModule,
                frameworkModule,
                databaseModule,
                cartModule,
                addressModule,
                orderModule,
                historyModule
            )
        }
    }
}
