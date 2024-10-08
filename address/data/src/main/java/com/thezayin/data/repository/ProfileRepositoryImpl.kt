package com.thezayin.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.thezayin.databases.dao.ProfileDao
import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of ProfileRepository handling data operations with Room and Firestore.
 *
 * @property profileDao DAO for local database operations.
 * @property firestore Instance of FirebaseFirestore for remote database operations.
 */
@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao,
    private val firestore: FirebaseFirestore
) : ProfileRepository {

    /**
     * Adds a new profile to both local Room database and Firestore.
     */
    override fun addProfile(
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String?
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            // Create Profile instance
            val profileModel = ProfileModel(
                name = name,
                phoneNumber = phoneNumber,
                address = address,
                area = area,
                city = city,
                email = email
            )
            // Insert into local database
            profileDao.insertProfile(profileModel)
            Timber.d("Profile added to local database: $profileModel")

            // Generate a unique ID for Firestore document
            val firestoreDocRef = firestore.collection("user_info").document()
            val firestoreProfile = profileModel.copy(id = firestoreDocRef.id.hashCode()) // Ensure unique ID if necessary

            // Insert into Firestore
            firestoreDocRef.set(firestoreProfile).await()
            Timber.d("Profile added to Firestore with ID: ${firestoreDocRef.id}")

            emit(Resource.Success(true))
        } catch (e: Exception) {
            Timber.e(e, "Error adding profile")
            emit(Resource.Error("Failed to add profile: ${e.localizedMessage}"))
        }
    }

    /**
     * Updates an existing profile in the local Room database.
     * Optionally synchronizes the update with Firestore.
     */
    override fun updateProfile(profileModel: ProfileModel): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            // Update in local database
            profileDao.updateProfile(profileModel)
            Timber.d("Profile updated in local database: $profileModel")

            // Optionally, update in Firestore
            // Uncomment the following lines if Firestore synchronization is required
            /*
            firestore.collection("user_info")
                .document(profile.id.toString())
                .set(profile)
                .await()
            Timber.d("Profile updated in Firestore: ${profile.id}")
            */

            emit(Resource.Success(true))
        } catch (e: Exception) {
            Timber.e(e, "Error updating profile")
            emit(Resource.Error("Failed to update profile: ${e.localizedMessage}"))
        }
    }

    /**
     * Deletes a profile from the local Room database.
     * Optionally deletes the profile from Firestore.
     */
    override fun deleteProfileById(id: Int): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            // Delete from local database
            profileDao.deleteProfileById(id)
            Timber.d("Profile with ID $id deleted from local database")

            // Optionally, delete from Firestore
            // Uncomment the following lines if Firestore synchronization is required
            /*
            firestore.collection("user_info")
                .document(id.toString())
                .delete()
                .await()
            Timber.d("Profile with ID $id deleted from Firestore")
            */

            emit(Resource.Success(true))
        } catch (e: Exception) {
            Timber.e(e, "Error deleting profile")
            emit(Resource.Error("Failed to delete profile: ${e.localizedMessage}"))
        }
    }

    /**
     * Deletes all profiles from the local Room database.
     * Optionally deletes all profiles from Firestore.
     */
    override fun deleteAllProfiles(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            // Delete all profiles from local database
            profileDao.deleteAllProfiles()
            Timber.d("All profiles deleted from local database")

            // Optionally, delete all profiles from Firestore
            // Caution: Deleting all documents from Firestore can be expensive and may require batch operations
            /*
            val snapshot = firestore.collection("user_info").get().await()
            val batch = firestore.batch()
            for (document in snapshot.documents) {
                batch.delete(document.reference)
            }
            batch.commit().await()
            Timber.d("All profiles deleted from Firestore")
            */

            emit(Resource.Success(true))
        } catch (e: Exception) {
            Timber.e(e, "Error deleting all profiles")
            emit(Resource.Error("Failed to delete all profiles: ${e.localizedMessage}"))
        }
    }

    /**
     * Retrieves all profiles from the local Room database.
     */
    override fun getAllProfiles(): Flow<Resource<List<ProfileModel>>> = flow {
        emit(Resource.Loading)
        try {
            // Fetch all profiles from local database
            val profiles = profileDao.getAllProfiles()
            Timber.d("Retrieved ${profiles.size} profiles from local database")
            emit(Resource.Success(profiles))
        } catch (e: Exception) {
            Timber.e(e, "Error retrieving all profiles")
            emit(Resource.Error("Failed to retrieve profiles: ${e.localizedMessage}"))
        }
    }

    /**
     * Retrieves a specific profile by its ID from the local Room database.
     */
    override fun getProfileById(id: Int): Flow<Resource<ProfileModel>> = flow {
        emit(Resource.Loading)
        try {
            // Fetch profile by ID from local database
            val profile = profileDao.getProfileById(id)
            if (profile != null) {
                Timber.d("Retrieved profile: $profile")
                emit(Resource.Success(profile))
            } else {
                Timber.w("Profile with ID $id not found")
                emit(Resource.Error("Profile with ID $id not found"))
            }
        } catch (e: Exception) {
            Timber.e(e, "Error retrieving profile by ID")
            emit(Resource.Error("Failed to retrieve profile: ${e.localizedMessage}"))
        }
    }
}
