package com.thezayin.databases.databasae

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.entities.ProfileModel

@Database(
    entities = [ProfileModel::class],
    version = 3,
    exportSchema = false
)
abstract class ProfileDatabase : RoomDatabase() {

}