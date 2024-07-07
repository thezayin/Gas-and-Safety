package com.thezayin.data.databasae

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.data.dao.ProfileDao
import com.thezayin.entities.ProfileModel

@Database(
    entities = [com.thezayin.entities.ProfileModel::class],
    version = 3,
    exportSchema = false
)
abstract class ProfileDatabase:RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}