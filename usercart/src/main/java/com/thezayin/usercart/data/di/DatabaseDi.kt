package com.thezayin.usercart.data.di

import android.content.Context
import androidx.room.Room
import com.thezayin.usercart.data.database.CartDatabase

fun provideCartDatabase(context: Context) =
    Room.databaseBuilder(context, CartDatabase::class.java, "safe_gas_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideCartDao(database: CartDatabase) = database.cartDao()