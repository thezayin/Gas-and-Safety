package com.thezayin.lpg.common.application

import android.app.Application
import com.thezayin.analytics.di.analyticsHelperModule
import com.thezayin.di.adminAddProductModule
import com.thezayin.di.adminHomeModule
import com.thezayin.di.adminProUpdateModule
import com.thezayin.di.adminProductModule
import com.thezayin.di.appModule
import com.thezayin.di.buyModule
import com.thezayin.di.dbModule
import com.thezayin.di.getUserOrdersModule
import com.thezayin.di.historyModule
import com.thezayin.di.userCartModule
import com.thezayin.di.userHomeModule
import com.thezayin.di.userProfile
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
            modules(buyModule)
            modules(dbModule)
        }
    }
}