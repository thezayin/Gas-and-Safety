package com.thezayin.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.databases.model.ProfileModel

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProfile(profileModel: ProfileModel)

    @Query("SELECT * FROM address_table ")
    fun getAllProfiles(): List<ProfileModel>

    @Query("SELECT * FROM address_table WHERE id = :id")
    fun getProfile(id: Int): ProfileModel

    @Query("DELETE FROM address_table")
    suspend fun deleteAllProfiles()

    @Query("DELETE FROM address_table WHERE id = :id")
    suspend fun deleteProfileById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfileById(
        profileModel: ProfileModel
    )
}