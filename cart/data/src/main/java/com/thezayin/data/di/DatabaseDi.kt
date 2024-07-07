package com.thezayin.data.di

import com.thezayin.data.database.CartDatabase

fun provideCartDao(database: CartDatabase) = database.cartDao()