package com.thezayin.data.di

import android.content.Context
import androidx.room.Room
import com.thezayin.data.databasae.ProfileDatabase

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, ProfileDatabase::class.java, "safe_gas_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideProfileDao(database: ProfileDatabase) = database.profileDao()