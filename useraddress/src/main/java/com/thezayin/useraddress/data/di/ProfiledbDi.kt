package com.thezayin.useraddress.data.di

import android.content.Context
import androidx.room.Room
import com.thezayin.useraddress.data.databasae.ProfileDatabase

fun provideProfileDatabase(context: Context) =
    Room.databaseBuilder(context, ProfileDatabase::class.java, "safe_gas_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideProfileDao(database: ProfileDatabase) = database.profileDao()