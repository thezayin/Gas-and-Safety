package com.thezayin.databases.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Data class representing a user profile.
 *
 * @property id Unique identifier for the profile. Auto-generated.
 * @property name Full name of the user.
 * @property phoneNumber Contact phone number.
 * @property address Residential address.
 * @property area Geographical area.
 * @property city City of residence.
 * @property email Optional email address.
 */
@Entity(tableName = "profiles")
@Serializable
data class ProfileModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Changed to non-nullable with a default value for better handling
    val name: String,
    val phoneNumber: String,
    val address: String,
    val area: String,
    val city: String,
    val email: String? = null
)
