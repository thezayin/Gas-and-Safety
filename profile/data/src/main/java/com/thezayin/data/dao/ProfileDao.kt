package com.thezayin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.ProfileModel

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProfile(profileModel: com.thezayin.entities.ProfileModel)

    @Query("SELECT * FROM profile_table ")
    fun getAllProfiles(): List<com.thezayin.entities.ProfileModel>

    @Query("SELECT * FROM profile_table WHERE id = :id")
    fun getProfile(id: Int): com.thezayin.entities.ProfileModel

    @Query("DELETE FROM profile_table")
    suspend fun deleteAllProfiles()

    @Query("DELETE FROM profile_table WHERE id = :id")
    suspend fun deleteProfileById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfileById(
        profileModel: com.thezayin.entities.ProfileModel
    )
}