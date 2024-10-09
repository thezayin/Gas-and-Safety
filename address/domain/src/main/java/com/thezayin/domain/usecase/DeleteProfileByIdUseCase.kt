package com.thezayin.domain.usecase

import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case for deleting a specific user profile by its unique identifier.
 *
 * This interface defines the contract for removing a single user profile from the data source
 * based on its unique ID. Implementations of this use case should handle the necessary business
 * logic and data interactions.
 */
interface DeleteProfileByIdUseCase {
    /**
     * Executes the use case to delete a specific user profile.
     *
     * @param params The parameters required to identify and delete the profile, encapsulated in [DeleteProfileByIdParams].
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    fun execute(params: DeleteProfileByIdParams): Flow<Resource<Boolean>>
}

/**
 * Data class representing the parameters required to delete a user profile.
 *
 * @property id The unique identifier of the profile to delete.
 */
data class DeleteProfileByIdParams(
    val id: Int
)

/**
 * Implementation of the [DeleteProfileByIdUseCase] interface.
 *
 * This class handles the deletion of a specific user profile by interacting with the [ProfileRepository].
 *
 * @property profileRepository The repository responsible for profile-related data operations.
 */
class DeleteProfileByIdUseCaseImpl(
    private val profileRepository: ProfileRepository
) : DeleteProfileByIdUseCase {

    /**
     * Executes the use case to delete a specific user profile.
     *
     * @param params The parameters required to identify and delete the profile, encapsulated in [DeleteProfileByIdParams].
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    override fun execute(params: DeleteProfileByIdParams): Flow<Resource<Boolean>> =
        profileRepository.deleteProfileById(params.id)
}