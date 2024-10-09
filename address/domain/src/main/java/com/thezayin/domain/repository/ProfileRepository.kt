package com.thezayin.domain.repository

import com.thezayin.databases.model.ProfileModel
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for profile data operations.
 */
interface ProfileRepository {

    /**
     * Adds a new profile.
     *
     * @param name User's full name.
     * @param phoneNumber User's phone number.
     * @param address User's address.
     * @param area User's area.
     * @param city User's city.
     * @param email Optional email address.
     * @return A Flow emitting Resource<Boolean> indicating success or failure.
     */
    fun addProfile(
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String?
    ): Flow<Resource<Boolean>>

    /**
     * Updates an existing profile by its ID.
     *
     * @param profileModel The profile with updated information.
     * @return A Flow emitting Resource<Boolean> indicating success or failure.
     */
    fun updateProfile(profileModel: ProfileModel): Flow<Resource<Boolean>>

    /**
     * Deletes a profile by its ID.
     *
     * @param id Unique identifier of the profile to delete.
     * @return A Flow emitting Resource<Boolean> indicating success or failure.
     */
    fun deleteProfileById(id: Int): Flow<Resource<Boolean>>

    /**
     * Deletes all profiles.
     *
     * @return A Flow emitting Resource<Boolean> indicating success or failure.
     */
    fun deleteAllProfiles(): Flow<Resource<Boolean>>

    /**
     * Retrieves all profiles.
     *
     * @return A Flow emitting Resource containing a list of Profile entities or an error.
     */
    fun getAllProfiles(): Flow<Resource<List<ProfileModel>>>

    /**
     * Retrieves a profile by its ID.
     *
     * @param id Unique identifier of the profile.
     * @return A Flow emitting Resource containing the Profile entity or an error.
     */
    fun getProfileById(id: Int): Flow<Resource<ProfileModel>>
}
