package com.thezayin.databases.databasae

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.databases.dao.CartDao
import com.thezayin.databases.dao.ProfileDao
import com.thezayin.entities.CartModel
import com.thezayin.entities.ProfileModel

@Database(
    entities = [CartModel::class, ProfileModel::class],
    version = 5,
    exportSchema = false
)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun profileDao(): ProfileDao
}