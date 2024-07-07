package com.thezayin.lpg.common.application

import android.app.Application
import com.thezayin.analytics.di.analyticsHelperModule
import com.thezayin.lpg.common.maps.di.mapsModule
import com.thezayin.lpg.di.adminAddProductModule
import com.thezayin.lpg.di.adminHomeModule
import com.thezayin.lpg.di.adminProUpdateModule
import com.thezayin.lpg.di.adminProductModule
import com.thezayin.lpg.di.appModule
import com.thezayin.lpg.di.getUserOrdersModule
import com.thezayin.lpg.di.historyModule
import com.thezayin.lpg.di.loginModule
import com.thezayin.lpg.di.placeOrderViewModelModule
import com.thezayin.lpg.di.userCartModule
import com.thezayin.lpg.di.userHomeModule
import com.thezayin.lpg.di.userProfile
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
            modules(mapsModule)
            modules(loginModule)
            modules(userProfile)
            modules(historyModule)
            modules(userCartModule)
            modules(userHomeModule)
            modules(adminHomeModule)
            modules(adminProductModule)
            modules(getUserOrdersModule)
            modules(adminProUpdateModule)
            modules(analyticsHelperModule)
            modules(adminAddProductModule)
            modules(placeOrderViewModelModule)
        }
    }
}