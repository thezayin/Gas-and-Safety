package com.thezayin.domain.usecase

import com.thezayin.databases.model.ProfileModel
import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flowOn

/**
 * Use case for updating a user profile by its unique identifier.
 *
 * This interface defines the contract for updating a single user profile in the data source
 * based on the provided profile ID and updated information. Implementations of this use case should
 * handle the necessary business logic and data interactions.
 */
interface UpdateProfileUseCase {
    /**
     * Executes the use case to update a specific user profile.
     *
     * @param params The parameters required to update the profile, encapsulated in [UpdateProfileParams].
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    fun execute(params: UpdateProfileParams): Flow<Resource<Boolean>>
}

/**
 * Data class representing the parameters required to update a user profile.
 *
 * @property id The unique identifier of the profile to update.
 * @property name The updated name of the user.
 * @property phoneNumber The updated phone number of the user.
 * @property address The updated address of the user.
 * @property area The updated geographical area of the user.
 * @property city The updated city of residence of the user.
 * @property email The updated email of the user. Optional.
 */
data class UpdateProfileParams(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val address: String,
    val area: String,
    val city: String,
    val email: String? = null
)

/**
 * Implementation of the [UpdateProfileUseCase] interface.
 *
 * This class handles the updating of a user profile by interacting with the [ProfileRepository].
 *
 * @property profileRepository The repository responsible for profile-related data operations.
 */
class UpdateProfileUseCaseImpl(
    private val profileRepository: ProfileRepository
) : UpdateProfileUseCase {

    /**
     * Executes the use case to update a specific user profile.
     *
     * @param params The parameters required to update the profile, encapsulated in [UpdateProfileParams].
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    override fun execute(params: UpdateProfileParams): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading) // Indicate that the operation has started
        try {
            // Map UpdateProfileParams to ProfileModel
            val profileModel = ProfileModel(
                id = params.id,
                name = params.name,
                phoneNumber = params.phoneNumber,
                address = params.address,
                area = params.area,
                city = params.city,
                email = params.email
            )
            // Initiate the profile update in the repository
            val responseFlow = profileRepository.updateProfile(profileModel)
            emitAll(responseFlow) // Emit the response from the repository
        } catch (e: Exception) {
            // Emit an error response if the update fails
            emit(Resource.Error("Failed to update profile with ID ${params.id}: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO) // Perform the operation on the IO dispatcher
}