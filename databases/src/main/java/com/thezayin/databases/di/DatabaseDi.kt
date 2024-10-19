package com.thezayin.databases.di

import android.content.Context
import androidx.room.Room
import com.thezayin.databases.database.AppDatabase
import com.thezayin.databases.dao.CartDao
import com.thezayin.databases.dao.ProfileDao

/**
 * Provides the Room database instance and DAOs for the application.
 *
 * This module defines functions to create and supply the [AppDatabase],
 * [CartDao], and [ProfileDao] instances.
 *
 * @param context The application context used to build the Room database.
 */

/**
 * Builds and provides the singleton instance of [AppDatabase].
 *
 * @param context The application context used to build the Room database.
 * @return A singleton instance of [AppDatabase].
 */
fun provideAppDatabase(context: Context): AppDatabase {

    return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "safe_gas_database" // Name of the database file
    )
        // **Caution:** Allowing main thread queries can lead to poor performance.
        // It's recommended to perform database operations on a background thread.
        .allowMainThreadQueries()

        // **Caution:** Fallback to destructive migration will delete all data
        // if no migration path is provided. Use with care in production.
        .fallbackToDestructiveMigration()

        // Optional: Add migrations here if you plan to handle schema changes.
        // .addMigrations(MIGRATION_1_2, MIGRATION_2_3, ...)

        .build()
}

/**
 * Provides the [CartDao] instance from the [AppDatabase].
 *
 * @param database The [AppDatabase] instance.
 * @return An instance of [CartDao].
 */
fun provideCartDao(database: AppDatabase): CartDao {
    return database.cartDao()
}

/**
 * Provides the [ProfileDao] instance from the [AppDatabase].
 *
 * @param database The [AppDatabase] instance.
 * @return An instance of [ProfileDao].
 */
fun provideProfileDao(database: AppDatabase): ProfileDao {
    return database.profileDao()
}