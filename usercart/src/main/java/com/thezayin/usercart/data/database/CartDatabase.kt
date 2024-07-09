package com.thezayin.usercart.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.usercart.data.dao.CartDao
import com.thezayin.entities.CartModel

@Database(
    entities = [CartModel::class],
    version = 5,
    exportSchema = false
)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}