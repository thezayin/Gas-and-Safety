package com.thezayin.databases.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.databases.dao.CartDao
import com.thezayin.databases.dao.ProfileDao
import com.thezayin.databases.model.CartModel
import com.thezayin.databases.model.ProfileModel

/**
 * The main Room database for the application.
 *
 * This database includes the following entities:
 * - [CartModel]: Represents the items in the shopping cart.
 * - [ProfileModel]: Represents user profiles.
 *
 * @property cartDao Provides access to cart-related database operations.
 * @property profileDao Provides access to profile-related database operations.
 */
@Database(
    entities = [CartModel::class, ProfileModel::class],
    version = 8, // Increment this number when making schema changes
    exportSchema = false // Set to true to export the schema for version control
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Retrieves the [CartDao] for performing cart-related database operations.
     *
     * @return An instance of [CartDao].
     */
    abstract fun cartDao(): CartDao

    /**
     * Retrieves the [ProfileDao] for performing profile-related database operations.
     *
     * @return An instance of [ProfileDao].
     */
    abstract fun profileDao(): ProfileDao

    companion object {
        // You can implement the Singleton pattern here if needed
        // to ensure only one instance of the database exists.
        //
        // Example using a simple Singleton approach:
        //
        // @Volatile
        // private var INSTANCE: AppDatabase? = null
        //
        // fun getDatabase(context: Context): AppDatabase {
        //     return INSTANCE ?: synchronized(this) {
        //         val instance = Room.databaseBuilder(
        //             context.applicationContext,
        //             AppDatabase::class.java,
        //             "app_database"
        //         )
        //         .addMigrations(MIGRATION_6_7) // Define your migrations
        //         .build()
        //         INSTANCE = instance
        //         instance
        //     }
        // }

        // Define your migration strategies here if upgrading the database version
        // For example:
        //
        // val MIGRATION_6_7 = object : Migration(6, 7) {
        //     override fun migrate(database: SupportSQLiteDatabase) {
        //         // Migration logic here
        //         database.execSQL("ALTER TABLE address_table ADD COLUMN firestoreId TEXT")
        //     }
        // }
    }
}
