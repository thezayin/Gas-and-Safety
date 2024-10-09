package com.thezayin.domain.usecase

import com.thezayin.domain.repository.ProfileRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case for deleting all user profiles.
 *
 * This interface defines the contract for removing all user profiles from the data source.
 * Implementations of this use case should handle the necessary business logic and data interactions.
 */
interface DeleteAllProfilesUseCase {
    /**
     * Executes the use case to delete all user profiles.
     *
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    fun execute(): Flow<Resource<Boolean>>
}

/**
 * Implementation of the [DeleteAllProfilesUseCase] interface.
 *
 * This class handles the deletion of all user profiles by interacting with the [ProfileRepository].
 *
 * @property profileRepository The repository responsible for profile-related data operations.
 */
class DeleteAllProfilesUseCaseImpl(
    private val profileRepository: ProfileRepository
) : DeleteAllProfilesUseCase {

    /**
     * Executes the use case to delete all user profiles.
     *
     * @return A [Flow] emitting a [Resource] indicating the success or failure of the operation.
     */
    override fun execute(): Flow<Resource<Boolean>> = profileRepository.deleteAllProfiles()
}
