package com.thezayin.databases.di

import android.content.Context
import androidx.room.Room
import com.thezayin.databases.dao.CartDao
import com.thezayin.databases.dao.ProfileDao
import com.thezayin.databases.database.AppDatabase
import org.koin.dsl.module

/**
 * Koin module for providing database-related dependencies.
 *
 * This module defines how to instantiate and provide the Room database and its DAOs.
 * It ensures that a single instance of the database and DAOs is used throughout the application.
 */
val databaseModule = module {

    /**
     * Provides a singleton instance of [AppDatabase].
     *
     * The Room database is built with the application context and includes the necessary DAOs.
     *
     * **Important:**
     * - **allowMainThreadQueries()** is enabled here for simplicity. However, it's **not recommended** for production
     *   as it can lead to poor performance and UI blocking. Consider removing it and performing database operations
     *   on a background thread using coroutines or other asynchronous mechanisms.
     * - **fallbackToDestructiveMigration()** is used to handle schema migrations by recreating the database.
     *   This will **delete all existing data** if a migration path isn't provided. It's suitable for development but
     *   should be handled carefully in production to avoid data loss.
     *
     * @param context The Android [Context], provided by Koin's [ApplicationContext] qualifier.
     * @return A singleton instance of [AppDatabase].
     */
    single<AppDatabase> {
        Room.databaseBuilder(
            get<Context>(), // Retrieves the application context from Koin
            AppDatabase::class.java, "safe_gas_database" // Name of the database file
        ).allowMainThreadQueries() // **Caution:** Remove in production for better performance
            .fallbackToDestructiveMigration() // **Caution:** Handle migrations properly to avoid data loss
            //.addMigrations(MIGRATION_6_7, MIGRATION_7_8) // Uncomment and define migrations as needed
            .build()
    }

    /**
     * Provides a singleton instance of [CartDao].
     *
     * DAOs are responsible for defining methods that access the database. By providing them as singletons,
     * you ensure that all parts of the application use the same DAO instance, maintaining consistency.
     *
     * @param database The [AppDatabase] instance, provided by Koin.
     * @return An instance of [CartDao].
     */
    single<CartDao> {
        get<AppDatabase>().cartDao()
    }

    /**
     * Provides a singleton instance of [ProfileDao].
     *
     * DAOs are responsible for defining methods that access the database. By providing them as singletons,
     * you ensure that all parts of the application use the same DAO instance, maintaining consistency.
     *
     * @param database The [AppDatabase] instance, provided by Koin.
     * @return An instance of [ProfileDao].
     */
    single<ProfileDao> {
        get<AppDatabase>().profileDao()
    }
}