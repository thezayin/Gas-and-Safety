package com.thezayin.databases.di

import android.content.Context
import androidx.room.Room
import com.thezayin.databases.databasae.CartDatabase

fun provideCartDatabase(context: Context) =
    Room.databaseBuilder(context, CartDatabase::class.java, "safe_gas_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideCartDao(database: CartDatabase) = database.cartDao()
fun provideProfileDao(database: CartDatabase) = database.profileDao()