package com.thezayin.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.databases.model.ProfileModel

/**
 * DAO interface for performing CRUD operations on Profile entities.
 */
@Dao
interface ProfileDao {

    /**
     * Inserts a new profile or replaces an existing one on conflict.
     *
     * @param profileModel The profile to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profileModel: ProfileModel)

    /**
     * Retrieves all profiles from the database.
     *
     * @return A list of Profile entities.
     */
    @Query("SELECT * FROM profiles")
    suspend fun getAllProfiles(): List<ProfileModel>

    /**
     * Retrieves a specific profile by its ID.
     *
     * @param id The unique identifier of the profile.
     * @return The Profile entity with the specified ID, or null if not found.
     */
    @Query("SELECT * FROM profiles WHERE id = :id")
    suspend fun getProfileById(id: Int): ProfileModel?

    /**
     * Deletes a specific profile by its ID.
     *
     * @param id The unique identifier of the profile to be deleted.
     */
    @Query("DELETE FROM profiles WHERE id = :id")
    suspend fun deleteProfileById(id: Int)

    /**
     * Deletes all profiles from the database.
     */
    @Query("DELETE FROM profiles")
    suspend fun deleteAllProfiles()

    /**
     * Updates an existing profile. Replaces the profile with the same ID.
     *
     * @param profileModel The profile with updated information.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfile(profileModel: ProfileModel)
}